package dz.esi.dossiermedical.dao;


import dz.esi.dossiermedical.model.InformationPersonnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InformationPersonnelleRepository extends JpaRepository<InformationPersonnelle, Long> {


    Optional<InformationPersonnelle> findByNumeroSecuriteSocial(Long numeroSecuriteSocial);

}
