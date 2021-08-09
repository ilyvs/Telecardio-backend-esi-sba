package dz.esi.msem.dao;


import dz.esi.msem.model.Abdominal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbdominalRepository extends JpaRepository<Abdominal, Long> {
}
