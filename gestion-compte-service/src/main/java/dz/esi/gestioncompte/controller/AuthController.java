package dz.esi.gestioncompte.controller;

import dz.esi.gestioncompte.DTO.DoctorCHU;
import dz.esi.gestioncompte.DTO.MicroserviceCallBody;
import dz.esi.gestioncompte.model.*;
import dz.esi.gestioncompte.payload.request.LoginRequest;
import dz.esi.gestioncompte.payload.request.SignupRequest;
import dz.esi.gestioncompte.payload.response.JwtResponse;
import dz.esi.gestioncompte.payload.response.MessageResponse;
import dz.esi.gestioncompte.repository.RoleRepository;
import dz.esi.gestioncompte.repository.SocialNumRepository;
import dz.esi.gestioncompte.repository.UserRepository;
import dz.esi.gestioncompte.security.jwt.JwtUtils;
import dz.esi.gestioncompte.security.services.UserDetailsImpl;
import dz.esi.gestioncompte.security.services.Userservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepo;


	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	SocialNumRepository socialNumRepository;

	 @Autowired
	 Userservices service;
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;


	@GetMapping("/users")
	public List<User> getUsers(){
		return userRepository.findAll();
	}

	@PostMapping("/get-information-personnelle")
	public User getInformationPersonnelle(@Valid @RequestBody MicroserviceCallBody microserviceCallBody) {
		return userRepository.findByNumeroSecuriteSocial(microserviceCallBody.getNumeroSecuriteSocial()).orElse(null);
	}

	@PostMapping("/get-all-doctors")
	public List<DoctorCHU> getAlldoc() {
		Role doc = new Role();
		doc.setName(ERole.ROLE_Medecin);
		doc.setId(1L);
		List<User> users = userRepo.getbyrole(doc);
		List<DoctorCHU> docs = new ArrayList<>();
		for(User user : users){
			if(user.getRoles().toString().equals("[Role(id=1, name=ROLE_Medecin)]")){
				docs.add( new DoctorCHU(user.getId(),user.getNom()));
			}
		}
		return docs;
	}

	@PostMapping("/get-all-nss")
	public List<SocialNum> getAllNSS() {
		return socialNumRepository.findAll();
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest)
			throws UnsupportedEncodingException, MessagingException {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));


		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);


		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getNom(),
				userDetails.getPrenom(),
				userDetails.getDateNaissance(),
				userDetails.getLieuNaissance(),
				userDetails.getEmail(),
				userDetails.getSex(),
				userDetails.getNumTelephone(),
				userDetails.getActiviteProf(),
				userDetails.getNumeroSecuriteSocial(),
				userDetails.getGroupeSanguin(),
				roles));

	}


	@CrossOrigin(origins = "*")
	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request)
			throws UnsupportedEncodingException, MessagingException {


		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		if (userRepository.existsByNumeroSecuriteSocial(signUpRequest.getNumeroSecuriteSocial())){
			return ResponseEntity
					.badRequest()
					.body((new MessageResponse("Error: Numero Securite Social is already in use")));
		}

		if (socialNumRepository.existsByNumeroSecuriteSocial(signUpRequest.getNumeroSecuriteSocial())) {

		}else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Wrong social number"));
		}


		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getNom(),
				signUpRequest.getPrenom(),
				signUpRequest.getDateNaissance(),
				signUpRequest.getLieuNaissance(),
				signUpRequest.getEmail(),
				signUpRequest.getSex(),
				signUpRequest.getNumTelephone(),
				signUpRequest.getActiviteProf(),
				signUpRequest.getNumeroSecuriteSocial(),
				signUpRequest.getGroupeSanguin(),
				encoder.encode(signUpRequest.getPassword())
		);


		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();


		if (strRoles == null) {
			Role patientRole = roleRepository.findByName(ERole.ROLE_Patient)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(patientRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_Admin)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "medecin":
					Role medecinRole = roleRepository.findByName(ERole.ROLE_Medecin)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(medecinRole);

					break;
					case "infermier":
						Role infermierRole = roleRepository.findByName(ERole.ROLE_Infermier)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(infermierRole);

						break;
				default:
					Role patientRole = roleRepository.findByName(ERole.ROLE_Patient)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(patientRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		service.register(user, getSiteURL(request));
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}

	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}


	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if (service.verify(code)) {
			return "verify_success";
		} else {
			return "verify_fail";
		}
	}


	@PutMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, Date dateNaissance, String lieuNaissance,
							 String nom, String prenom, String email , String adresse, String numTelephone,
							 String password)
	{
		try {
			User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setDateNaissance(dateNaissance);
			user.setLieuNaissance(lieuNaissance);
			user.setEmail(email);
			user.setNumTelephone(numTelephone);
			user.setPassword(password);

			userRepository.save(user);
		}
		catch (Exception ex){
			return "Error updating the user: " + ex.toString();

		}
		return "user updated";


	}



	@GetMapping("/delete/{id}")
	@PreAuthorize("hasRole('Admin')")
	public String deleteUser(@PathVariable("id") long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		userRepository.delete(user);

		return "user deleted";
	}

}
