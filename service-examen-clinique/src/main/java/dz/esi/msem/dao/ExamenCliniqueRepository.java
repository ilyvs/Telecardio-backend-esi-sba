package dz.esi.msem.dao;

import dz.esi.msem.model.ExamenClinique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamenCliniqueRepository extends JpaRepository<ExamenClinique, Long> {

}
