package dz.esi.examenclinique.controller;

import dz.esi.examenclinique.model.ExamenClinique;
import dz.esi.examenclinique.proxy.MicroserviceCallBody;
import dz.esi.examenclinique.service.ExamenCliniqueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class ExamenCliniqueController {


    @Autowired
    private ExamenCliniqueService examenMedicalService;


    @GetMapping("/afficher-examen-medical")
    public ExamenClinique afficherExamenMedical(@RequestBody MicroserviceCallBody microserviceCallBody) {
        return examenMedicalService.afficherExamenMedical(microserviceCallBody);
    }

    @PutMapping("/modifier-examen-medical/{id}")
    public ResponseEntity<?> modifierExamenMedical(@RequestBody final ExamenClinique newData,
                                                    @PathVariable Long id ) {
        return examenMedicalService.modifierExamenMedical(newData, id);
    }


}
