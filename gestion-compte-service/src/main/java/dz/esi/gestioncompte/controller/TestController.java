package dz.esi.gestioncompte.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {


	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('Patient') or hasRole('Medecin') or hasRole('Admin')")
	public String userAccess() {
		return "user Content.";
	}

	@GetMapping("/medecin")
	@PreAuthorize("hasRole('Medecin')")
	public String medecinAccess() {
		return "Medecin Board.";
	}

	@GetMapping("/patient")
	@PreAuthorize("hasRole('Patient')")
	public String patientAccess() {
		return "Patient Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('Admin')")
	public String adminAccess() {
		return "Admin Board.";
	}


	@GetMapping("/infermier")
	@PreAuthorize("hasRole('Infermier')")
	public String infermierAccess() {
		return "Infermier Board.";
	}
}
