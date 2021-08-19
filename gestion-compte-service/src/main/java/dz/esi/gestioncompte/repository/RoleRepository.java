package dz.esi.gestioncompte.repository;

import java.util.Optional;

import dz.esi.gestioncompte.model.ERole;
import dz.esi.gestioncompte.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
