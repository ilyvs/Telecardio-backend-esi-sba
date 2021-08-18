package dz.esi.dossiermedical.dao;

import dz.esi.dossiermedical.model.AntecedentMedicoCherigicaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AntecedentMedicoCherigicauxRepository extends JpaRepository<AntecedentMedicoCherigicaux, Long> {
}
