package dz.esi.msdm.dao;

import dz.esi.msdm.model.AntecedentMedicoCherigicaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AntecedentMedicoCherigicauxRepository extends JpaRepository<AntecedentMedicoCherigicaux, Long> {
}
