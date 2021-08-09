package dz.esi.msem.dao;


import dz.esi.msem.model.Pouls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoulsRepository  extends JpaRepository<Pouls, Long> {
}
