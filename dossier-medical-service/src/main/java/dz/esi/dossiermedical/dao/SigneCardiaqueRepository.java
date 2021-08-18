package dz.esi.dossiermedical.dao;

import dz.esi.dossiermedical.model.SigneCardiaque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SigneCardiaqueRepository extends JpaRepository<SigneCardiaque, Long> {
}
