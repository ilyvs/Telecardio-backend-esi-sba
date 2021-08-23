package dz.esi.dossiermedical.service;

import dz.esi.dossiermedical.DTO.DynamicInformationPersonnelle;
import dz.esi.dossiermedical.dao.*;
import dz.esi.dossiermedical.model.InformationPersonnelle;
import dz.esi.dossiermedical.model.PatientDossier;
import dz.esi.dossiermedical.proxy.InformationPersonnelleProxy;
import dz.esi.dossiermedical.proxy.MsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    public InformationPersonnelle getInformationPersonnelle(MsRequest msRequest) {
        DynamicInformationPersonnelle dynamicInformationPersonnelle = informationPersonnelleProxy.getInformationPersonnelle(msRequest);

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

        return informationPersonnelle;
    }



    @ResponseBody
    public ResponseEntity<?> ajouterDossierMedical(PatientDossier Data) {

        Data.getInformationPersonnelle().setId(Data.getInformationPersonnelle().getId());
        informationPersonnelleRepo.save(Data.getInformationPersonnelle());


        Data.getInformationBiometrique().setImc(Data.getInformationBiometrique().getPoids()/Math.sqrt(Data.getInformationBiometrique().getTaille()));
        biometriqueRepo.save(Data.getInformationBiometrique());

        antecedentPersonnelleRepo.save(Data.getAntecedentPersonnelle());

        signeCardiaqueRepo.save(Data.getSigneCardiaque());

        antecedentMedicoCherigicauxRepo.save(Data.getAntecedentMedicoCherigicaux());

        patientDossierRepo.save(Data);


        return new ResponseEntity<>("Dossier médical ajouté avec succès", HttpStatus.OK);
    }


    public InformationPersonnelle afficherDossierMedical(@PathVariable Long id) {
        InformationPersonnelle infPer = informationPersonnelleRepo.findById(id).orElseThrow (null);
        return infPer;
    }

    @ResponseBody
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
