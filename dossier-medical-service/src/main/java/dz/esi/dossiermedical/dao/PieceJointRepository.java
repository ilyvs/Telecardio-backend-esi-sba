package dz.esi.dossiermedical.dao;


import dz.esi.dossiermedical.model.PieceJoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PieceJointRepository extends JpaRepository<PieceJoint, Long> {

}
