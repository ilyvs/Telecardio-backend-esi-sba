package dz.esi.dossiermedical.service;

import dz.esi.dossiermedical.dao.*;
import dz.esi.dossiermedical.model.InformationPersonnelle;
import dz.esi.dossiermedical.model.PieceJoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
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
    private PieceJointRepository pieceJointRepo;


    @ResponseBody
    public ResponseEntity<?> ajouterDossierMedical(InformationPersonnelle Data) {

        Data.getInformationBiometrique().setImc(Data.getInformationBiometrique().getPoids()/Math.sqrt(Data.getInformationBiometrique().getTaille()));
        biometriqueRepo.save(Data.getInformationBiometrique());
        antecedentPersonnelleRepo.save(Data.getAntecedentPersonnelle());
        signeCardiaqueRepo.save(Data.getSigneCardiaque());
        antecedentMedicoCherigicauxRepo.save(Data.getAntecedentMedicoCherigicaux());
        informationPersonnelleRepo.save(Data);

        for (PieceJoint pieceJoint: Data.getPieceJointList()) {
            pieceJoint.setInfoPer(Data);
            pieceJointRepo.save(pieceJoint);
        }

        return new ResponseEntity<>("Dossier médical ajouté avec succès", HttpStatus.OK);
    }


    public InformationPersonnelle afficherDossierMedical(@PathVariable Long id) {
        InformationPersonnelle infPer = informationPersonnelleRepo.findById(id).orElseThrow (null);
        if ( infPer!=null ) for (PieceJoint pieceJoint : infPer.getPieceJointList()) pieceJoint.setInfoPer(null);
        return infPer;
    }

    @ResponseBody
    public ResponseEntity<?> modifierDossierMedical(InformationPersonnelle newData, Long id) {

        if (informationPersonnelleRepo.getById(id) != null) {
            newData.setId(id);

            newData.getInformationBiometrique().setImc(newData.getInformationBiometrique().getPoids()/Math.sqrt(newData.getInformationBiometrique().getTaille()));
            newData.getInformationBiometrique().setId(newData.getInformationBiometrique().getId());
            biometriqueRepo.save(newData.getInformationBiometrique());

            newData.getAntecedentPersonnelle().setId(newData.getAntecedentPersonnelle().getId());
            antecedentPersonnelleRepo.save(newData.getAntecedentPersonnelle());

            newData.getAntecedentMedicoCherigicaux().setId(newData.getAntecedentMedicoCherigicaux().getId());
            antecedentMedicoCherigicauxRepo.save(newData.getAntecedentMedicoCherigicaux());

            newData.getSigneCardiaque().setId(newData.getSigneCardiaque().getId());
            signeCardiaqueRepo.save(newData.getSigneCardiaque());

            informationPersonnelleRepo.save(newData);
            for (PieceJoint pieceJoint: newData.getPieceJointList()) {
                pieceJoint.setId(pieceJoint.getId());
                pieceJoint.setInfoPer(newData);
                pieceJointRepo.save(pieceJoint);
            }

        }
        return new ResponseEntity<>("Dossier médical modifié avec succès", HttpStatus.OK);
    }



}
