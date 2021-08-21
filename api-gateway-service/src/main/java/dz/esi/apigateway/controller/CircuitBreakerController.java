package dz.esi.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    @GetMapping("/dossierMedicalServiceDefaultResponse")
    public String dossierMedicalServiceFallBack() {
        return " Dossier Medical Service Goes Down";
    }

    @GetMapping("/examenCliniqueServiceDefaultResponse")
    public String examenCliniqueServiceFallBack() {
        return " Examen Clinique Service Goes Down";
    }

    @GetMapping("/gestionCompteServiceDefaultResponse")
    public String gestionCompteServiceFallBack() {
        return "Gestion Compte Service Goes Down";
    }

}
