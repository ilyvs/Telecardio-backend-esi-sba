package dz.esi.dossiermedical.dao;

import dz.esi.dossiermedical.model.InformationPersonnelle;
import dz.esi.dossiermedical.model.PatientDossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientDossierRepository extends JpaRepository<PatientDossier, Long> {

    Optional<PatientDossier> findByNumeroSecuriteSocial(Long numeroSecuriteSocial);


}
