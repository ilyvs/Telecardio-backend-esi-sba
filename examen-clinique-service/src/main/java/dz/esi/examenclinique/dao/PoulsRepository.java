package dz.esi.examenclinique.dao;


import dz.esi.examenclinique.model.Pouls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoulsRepository  extends JpaRepository<Pouls, Long> {
}
