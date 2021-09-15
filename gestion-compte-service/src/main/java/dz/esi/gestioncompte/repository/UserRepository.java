package dz.esi.gestioncompte.repository;
import dz.esi.gestioncompte.model.Role;
import dz.esi.gestioncompte.model.ERole;
import dz.esi.gestioncompte.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);


	Optional<User> findByNumeroSecuriteSocial(Long numeroSecuriteSocial);

	Boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	Boolean existsByNumeroSecuriteSocial(Long sin);



	@Query("SELECT a FROM User a, Role r   WHERE  r.id =1L "   )
	public List<User> getbyrole(Role doc);


	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
	public User findByVerificationCode(String code);


}

