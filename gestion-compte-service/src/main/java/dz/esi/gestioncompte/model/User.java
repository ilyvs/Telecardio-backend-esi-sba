package dz.esi.gestioncompte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String nom;

	private String prenom;

	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	private String lieuNaissance;

	private String email;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	private String numTelephone;

	private String activiteProf;

	private Long numeroSecuriteSocial;

	private String groupeSanguin;

	private String password;

	private String verificationCode;

	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public User(String username, String nom, String prenom, Date dateNaissance, String lieuNaissance,
				String email, Sex sex, String numTelephone, String activiteProf,
				Long numeroSecuriteSocial, String groupeSanguin, String password) {

		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.sex = sex;
		this.email = email;
		this.numTelephone = numTelephone;
		this.activiteProf = activiteProf;
		this.numeroSecuriteSocial = numeroSecuriteSocial;
		this.groupeSanguin = groupeSanguin;
		this.password = password;
	}



}
