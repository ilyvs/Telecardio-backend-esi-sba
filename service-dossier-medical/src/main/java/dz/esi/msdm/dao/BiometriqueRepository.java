package dz.esi.msdm.dao;


import dz.esi.msdm.model.InformationBiometrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BiometriqueRepository extends JpaRepository<InformationBiometrique, Long> {
}
