package dz.esi.dossiermedical.controller;

import dz.esi.dossiermedical.model.InformationPersonnelle;
import dz.esi.dossiermedical.model.PatientDossier;
import dz.esi.dossiermedical.service.DossierMedicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class DossierMedicalController {


    @Autowired
    private DossierMedicalService dossierMedicalService;


    @PostMapping(value = "/ajouter-dossier-medical")
    public ResponseEntity<?> ajouterDossierMedical(@RequestBody final PatientDossier Data) {
        return dossierMedicalService.ajouterDossierMedical(Data);
    }


    @GetMapping("/afficher-dossier-medical/{id}")
    public InformationPersonnelle afficherDossierMedical(@PathVariable Long id) {
        return dossierMedicalService.afficherDossierMedical(id);
    }

    @PutMapping("/modifier-dossier-medical/{id}")
    public ResponseEntity<?> modifierDossierMedical(@RequestBody final PatientDossier newData,
                                                    @PathVariable Long id ) {
        return dossierMedicalService.modifierDossierMedical(newData, id);
    }


}
