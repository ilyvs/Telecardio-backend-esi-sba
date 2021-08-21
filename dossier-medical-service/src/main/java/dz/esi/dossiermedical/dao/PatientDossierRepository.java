package dz.esi.dossiermedical.dao;

import dz.esi.dossiermedical.model.PatientDossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDossierRepository extends JpaRepository<PatientDossier, Long> {

}
