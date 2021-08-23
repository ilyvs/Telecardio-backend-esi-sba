package dz.esi.dossiermedical.controller;

import dz.esi.dossiermedical.model.InformationPersonnelle;
import dz.esi.dossiermedical.model.PatientDossier;
import dz.esi.dossiermedical.proxy.LoginRequest;
import dz.esi.dossiermedical.proxy.MsRequest;
import dz.esi.dossiermedical.service.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class DossierMedicalController {


    @Autowired
    private DossierMedicalService dossierMedicalService;



    @GetMapping("/get-information-personnelle")
    public InformationPersonnelle getInformationPersonnelle(@RequestBody MsRequest msRequest) {
        return dossierMedicalService.getInformationPersonnelle(msRequest);
    }


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
