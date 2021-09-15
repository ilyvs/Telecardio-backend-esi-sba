package dz.esi.dossiermedical.controller;

import dz.esi.dossiermedical.model.PatientDossier;
import dz.esi.dossiermedical.proxy.MicroserviceCallBody;
import dz.esi.dossiermedical.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class DossierMedicalController {


    @Autowired
    private DossierMedicalService dossierMedicalService;



    @GetMapping("/afficher-dossier-medical")
    public PatientDossier afficherDossierMedical(@RequestBody MicroserviceCallBody microserviceCallBody) {
        return dossierMedicalService.afficherDossierMedical(microserviceCallBody);
    }

    @PutMapping("/modifier-dossier-medical/{id}")
    public ResponseEntity<?> modifierDossierMedical(@RequestBody final PatientDossier newData,
                                                    @PathVariable Long id ) {
        return dossierMedicalService.modifierDossierMedical(newData, id);
    }


}
