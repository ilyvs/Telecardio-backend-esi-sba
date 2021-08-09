package dz.esi.msdm.dao;


import dz.esi.msdm.model.PieceJoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PieceJointRepository extends JpaRepository<PieceJoint, Long> {

}
