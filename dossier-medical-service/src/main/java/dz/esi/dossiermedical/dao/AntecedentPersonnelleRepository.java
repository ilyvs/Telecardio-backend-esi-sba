package dz.esi.dossiermedical.dao;

import dz.esi.dossiermedical.model.AntecedentPersonnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedentPersonnelleRepository extends JpaRepository<AntecedentPersonnelle, Long> {
}
