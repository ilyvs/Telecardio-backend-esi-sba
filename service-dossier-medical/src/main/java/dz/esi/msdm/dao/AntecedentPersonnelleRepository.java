package dz.esi.msdm.dao;

import dz.esi.msdm.model.AntecedentPersonnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedentPersonnelleRepository extends JpaRepository<AntecedentPersonnelle, Long> {
}
