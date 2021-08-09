package dz.esi.msem.dao;


import dz.esi.msem.model.Thorax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThoraxRepository extends JpaRepository<Thorax, Long>{
}
