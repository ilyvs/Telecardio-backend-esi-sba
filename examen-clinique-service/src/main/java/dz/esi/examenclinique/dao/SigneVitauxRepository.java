package dz.esi.examenclinique.dao;

import dz.esi.examenclinique.model.SigneVitaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SigneVitauxRepository extends JpaRepository<SigneVitaux, Long> {
}
