package dz.esi.examenclinique.dao;


import dz.esi.examenclinique.model.Poumons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoumonsRepository extends JpaRepository<Poumons, Long> {
}
