package dz.esi.msdm.dao;


import dz.esi.msdm.model.InformationPersonnelle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationPersonnelleRepository extends JpaRepository<InformationPersonnelle, Long> {

}
