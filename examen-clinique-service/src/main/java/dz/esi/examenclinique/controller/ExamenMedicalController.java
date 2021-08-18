package dz.esi.examenclinique.controller;

import dz.esi.examenclinique.model.ExamenClinique;
import dz.esi.examenclinique.service.ExamenMedicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/msem/service")
public class ExamenMedicalController {

    Logger logger = LoggerFactory.getLogger(ExamenMedicalController.class);

    @Autowired
    private ExamenMedicalService examenMedicalService;

    @PostMapping(value = "/ajouter-examen-medical", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ajouterExamenMedical(@RequestBody final ExamenClinique Data) {
        return examenMedicalService.ajouterExamenMedical(Data);
    }

    @GetMapping("/afficher-examen-medical/{id}")
    public ExamenClinique afficherExamenMedical(@PathVariable Long id) {
        return examenMedicalService.afficherExamenMedical(id);
    }

    @PutMapping("/modifier-examen-medical/{id}")
    public ResponseEntity<?> modifierExamenMedical(@RequestBody final ExamenClinique newData,
                                                    @PathVariable Long id ) {
        return examenMedicalService.modifierExamenMedical(newData, id);
    }


}
