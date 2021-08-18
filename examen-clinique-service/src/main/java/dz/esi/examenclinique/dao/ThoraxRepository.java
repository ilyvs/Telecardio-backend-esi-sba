package dz.esi.examenclinique.dao;


import dz.esi.examenclinique.model.Thorax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThoraxRepository extends JpaRepository<Thorax, Long>{
}
