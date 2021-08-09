package dz.esi.msem.service;

import dz.esi.msem.dao.*;
import dz.esi.msem.model.ExamenClinique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class ExamenMedicalService {


    @Autowired
    private ExamenCliniqueRepository examenCliniqueRepo;

    @Autowired
    private PoulsRepository poulsRepo;

    @Autowired
    private SigneVitauxRepository signeVitauxRepo;

    @Autowired
    private VeineRepository veineRepo;

    @Autowired
    private PoumonsRepository poumonsRepo;

    @Autowired
    private AbdominalRepository abdominalRepo;

    @Autowired
    private ThoraxRepository thoraxRepo;

    @ResponseBody
    public ResponseEntity<?> ajouterExamenMedical(ExamenClinique Data) {

        poulsRepo.save(Data.getPouls());
        signeVitauxRepo.save(Data.getSigneVitaux());
        veineRepo.save(Data.getVeine());
        poumonsRepo.save(Data.getPoumons());
        abdominalRepo.save(Data.getAbdominal());
        thoraxRepo.save(Data.getThorax());

        examenCliniqueRepo.save(Data);

        return new ResponseEntity<>("Examen clinique infos ajoutés avec succès", HttpStatus.OK);
    }


    public ExamenClinique afficherExamenMedical( Long id) {
        return examenCliniqueRepo.findById(id).orElse(null);
    }

    @ResponseBody
    public ResponseEntity<?> modifierExamenMedical(ExamenClinique newData, Long id) {

            if (examenCliniqueRepo.getById(id) != null) {
            newData.setId(id);

            newData.getPouls().setId(newData.getPouls().getId());
            poulsRepo.save(newData.getPouls());

            newData.getSigneVitaux().setId(newData.getSigneVitaux().getId());
            signeVitauxRepo.save(newData.getSigneVitaux());

            newData.getVeine().setId(newData.getVeine().getId());
            veineRepo.save(newData.getVeine());

                newData.getAbdominal().setId(newData.getAbdominal().getId());
                abdominalRepo.save(newData.getAbdominal());

                newData.getPoumons().setId(newData.getPoumons().getId());
                poumonsRepo.save(newData.getPoumons());

                newData.getThorax().setId(newData.getThorax().getId());
                thoraxRepo.save(newData.getThorax());

            examenCliniqueRepo.save(newData);

        }
        return new ResponseEntity<>("Examen clinique infos  modifiés avec succès", HttpStatus.OK);
    }



}
