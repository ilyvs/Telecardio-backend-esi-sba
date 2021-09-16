package dz.esi.examenclinique.dao;

import dz.esi.examenclinique.DTO.Nss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NssRepository extends JpaRepository<Nss,Long> {
}
