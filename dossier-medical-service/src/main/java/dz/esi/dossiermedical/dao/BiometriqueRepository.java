package dz.esi.dossiermedical.dao;


import dz.esi.dossiermedical.model.InformationBiometrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BiometriqueRepository extends JpaRepository<InformationBiometrique, Long> {
}
