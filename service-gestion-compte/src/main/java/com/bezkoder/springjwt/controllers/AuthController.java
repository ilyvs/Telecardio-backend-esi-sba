package com.bezkoder.springjwt.controllers;

import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.LoginRequest;
import com.bezkoder.springjwt.payload.request.SignupRequest;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.SocialNumRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import com.bezkoder.springjwt.security.services.Userservices;
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
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

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




	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, User user)
			throws UnsupportedEncodingException, MessagingException {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        //if (user.isEnabled()==true){

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(),
												 userDetails.getUsername(),
												 userDetails.getEmail(),
											     roles));

/**
		 }
        else return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: please verify your mail"));
**/

	}





	@PostMapping("/signup")
	public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest, HttpServletRequest request)
			throws UnsupportedEncodingException, MessagingException {

		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already exist!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		if (userRepository.existsBySin(signUpRequest.getSin())){
			return ResponseEntity
					.badRequest()
					.body((new MessageResponse("Error: Sin is already in use")));
		}

		if (socialNumRepository.existsBySin(signUpRequest.getSin())) {

		}else {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Wrong social number"));
		}


		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
									 signUpRequest.getEmail(),
									 signUpRequest.getDateN(),
									 signUpRequest.getNumTel(),
				signUpRequest.getSin(),
				encoder.encode(signUpRequest.getPassword()),
				signUpRequest.getSex());


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
	public String updateUser(@PathVariable("id") long id, String username, String email , Date dateN, String numTel, String password,Long sin)
	{
		try {
			User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
			user.setUsername(username);

			user.setEmail(email);
			user.setDateN(dateN);
			user.setNumTel(numTel);
			user.setPassword(password);
			user.setSin(sin);

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
