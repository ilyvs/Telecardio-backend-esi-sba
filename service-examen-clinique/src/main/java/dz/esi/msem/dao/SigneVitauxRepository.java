package dz.esi.msem.dao;

import dz.esi.msem.model.SigneVitaux;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SigneVitauxRepository extends JpaRepository<SigneVitaux, Long> {
}
