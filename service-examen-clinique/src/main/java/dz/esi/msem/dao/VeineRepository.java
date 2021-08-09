package dz.esi.msem.dao;

import dz.esi.msem.model.Veine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeineRepository extends JpaRepository<Veine, Long> {
}
