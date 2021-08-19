package dz.esi.gestioncompte.repository;

import dz.esi.gestioncompte.model.ERole;
import dz.esi.gestioncompte.model.Role;
import dz.esi.gestioncompte.model.SocialNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocialNumRepository extends JpaRepository<SocialNum, Long> {
    Optional<SocialNum> findBySin(SocialNum sin);

    Boolean existsBySin(Long sin);

}
