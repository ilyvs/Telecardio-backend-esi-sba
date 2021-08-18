package dz.esi.examenclinique.dao;


import dz.esi.examenclinique.model.Abdominal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbdominalRepository extends JpaRepository<Abdominal, Long> {
}
