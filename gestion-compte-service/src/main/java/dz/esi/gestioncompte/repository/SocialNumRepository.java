package dz.esi.gestioncompte.repository;


import dz.esi.gestioncompte.model.SocialNum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SocialNumRepository extends JpaRepository<SocialNum, Long> {

    Boolean existsByNumeroSecuriteSocial(Long numeroSecuriteSocial);

}
