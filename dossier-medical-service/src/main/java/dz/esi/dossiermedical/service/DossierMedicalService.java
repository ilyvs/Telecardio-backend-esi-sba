package dz.esi.dossiermedical.service;

import dz.esi.dossiermedical.DTO.DynamicInformationPersonnelle;
import dz.esi.dossiermedical.dao.*;
import dz.esi.dossiermedical.model.InformationPersonnelle;
import dz.esi.dossiermedical.model.PatientDossier;
import dz.esi.dossiermedical.proxy.InformationPersonnelleProxy;
import dz.esi.dossiermedical.proxy.MicroserviceCallBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DossierMedicalService {

    @Autowired
    private InformationPersonnelleRepository informationPersonnelleRepo;

    @Autowired
    private BiometriqueRepository biometriqueRepo;

    @Autowired
    private AntecedentPersonnelleRepository antecedentPersonnelleRepo;

    @Autowired
    private AntecedentMedicoCherigicauxRepository antecedentMedicoCherigicauxRepo;

    @Autowired
    private SigneCardiaqueRepository signeCardiaqueRepo;

    @Autowired
    InformationPersonnelleProxy informationPersonnelleProxy;

    @Autowired
    PatientDossierRepository patientDossierRepo;







    public InformationPersonnelle getInformationPersonnelle(MicroserviceCallBody microserviceCallBody) {
        DynamicInformationPersonnelle dynamicInformationPersonnelle = informationPersonnelleProxy.getInformationPersonnelle(microserviceCallBody);

        InformationPersonnelle informationPersonnelle = new InformationPersonnelle(
                dynamicInformationPersonnelle.getNom(),
                dynamicInformationPersonnelle.getPrenom(),
                dynamicInformationPersonnelle.getDateNaissance(),
                dynamicInformationPersonnelle.getLieuNaissance(),
                dynamicInformationPersonnelle.getSex(),
                dynamicInformationPersonnelle.getEmail(),
                dynamicInformationPersonnelle.getNumTelephone(),
                dynamicInformationPersonnelle.getActiviteProf(),
                dynamicInformationPersonnelle.getNumeroSecuriteSocial(),
                dynamicInformationPersonnelle.getGroupeSanguin()
        );
        informationPersonnelleRepo.save(informationPersonnelle);

         PatientDossier patientDossier = new PatientDossier();
         patientDossier.setInformationPersonnelle(informationPersonnelle);
         patientDossier.setNumeroSecuriteSocial(dynamicInformationPersonnelle.getNumeroSecuriteSocial());
         patientDossierRepo.save(patientDossier);


        return informationPersonnelle;
    }



    public PatientDossier afficherDossierMedical(MicroserviceCallBody microserviceCallBody) {

        InformationPersonnelle informationPersonnelle = informationPersonnelleRepo.findByNumeroSecuriteSocial(microserviceCallBody.getNumeroSecuriteSocial()).orElse(null);

        if (informationPersonnelle == null){
            getInformationPersonnelle(microserviceCallBody);
        }

        PatientDossier patientDossier = patientDossierRepo.findByNumeroSecuriteSocial(microserviceCallBody.getNumeroSecuriteSocial()).orElse(null);

        return patientDossier;
    }


    public ResponseEntity<?> modifierDossierMedical(PatientDossier newData, Long id) {

        if (patientDossierRepo.getById(id) != null) {
            newData.setId(id);

            newData.getInformationPersonnelle().setId(newData.getInformationPersonnelle().getId());
            informationPersonnelleRepo.save(newData.getInformationPersonnelle());

            newData.getInformationBiometrique().setImc(newData.getInformationBiometrique().getPoids()/Math.sqrt(newData.getInformationBiometrique().getTaille()));
            newData.getInformationBiometrique().setId(newData.getInformationBiometrique().getId());
            biometriqueRepo.save(newData.getInformationBiometrique());

            newData.getAntecedentPersonnelle().setId(newData.getAntecedentPersonnelle().getId());
            antecedentPersonnelleRepo.save(newData.getAntecedentPersonnelle());

            newData.getAntecedentMedicoCherigicaux().setId(newData.getAntecedentMedicoCherigicaux().getId());
            antecedentMedicoCherigicauxRepo.save(newData.getAntecedentMedicoCherigicaux());

            newData.getSigneCardiaque().setId(newData.getSigneCardiaque().getId());
            signeCardiaqueRepo.save(newData.getSigneCardiaque());

            patientDossierRepo.save(newData);

        }
        return new ResponseEntity<>("Dossier médical modifié avec succès", HttpStatus.OK);

    }



}
