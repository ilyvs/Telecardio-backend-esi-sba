package dz.esi.examenclinique.dao;

import dz.esi.examenclinique.model.ExamenClinique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExamenCliniqueRepository extends JpaRepository<ExamenClinique, Long> {

    Optional<ExamenClinique> findByNumeroSecuriteSocial(Long numeroSecuriteSocial);

}
