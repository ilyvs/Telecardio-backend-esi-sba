package dz.esi.examenclinique.dao;

import dz.esi.examenclinique.model.Veine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeineRepository extends JpaRepository<Veine, Long> {
}
