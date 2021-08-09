package dz.esi.msdm.dao;

import dz.esi.msdm.model.SigneCardiaque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SigneCardiaqueRepository extends JpaRepository<SigneCardiaque, Long> {
}
