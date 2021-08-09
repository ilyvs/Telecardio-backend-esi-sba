package dz.esi.msem.dao;


import dz.esi.msem.model.Poumons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoumonsRepository extends JpaRepository<Poumons, Long> {
}
