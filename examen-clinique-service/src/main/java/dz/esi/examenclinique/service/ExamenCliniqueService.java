package dz.esi.examenclinique.service;

import dz.esi.examenclinique.DTO.Nss;
import dz.esi.examenclinique.dao.*;
import dz.esi.examenclinique.model.ExamenClinique;
import dz.esi.examenclinique.proxy.MicroserviceCallBody;
import dz.esi.examenclinique.proxy.NssProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenCliniqueService {


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

    @Autowired
    private NssProxy nssProxy;

    @Autowired
    private NssRepository nssRepo;

    public void checkNssTable(){
        if (nssRepo.findAll()==null) {
            List<Nss> nssList = nssProxy.getAllNss();
            for (Nss nss : nssList)
                nssRepo.save(nss);
        }
    }

    public ExamenClinique afficherExamenMedical(MicroserviceCallBody microserviceCallBody) {

        //checkNssTable();

        ExamenClinique examenClinique =  examenCliniqueRepo.findByNumeroSecuriteSocial(microserviceCallBody.getNumeroSecuriteSocial()).orElse(null);
        if (examenClinique == null) {
            examenClinique = new ExamenClinique();
            examenClinique.setNumeroSecuriteSocial(microserviceCallBody.getNumeroSecuriteSocial());
            examenCliniqueRepo.save(examenClinique);
        }
        return examenClinique;
    }


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
        return new ResponseEntity<>("Examen clinique infos  modifi??s avec succ??s", HttpStatus.OK);
    }


}
