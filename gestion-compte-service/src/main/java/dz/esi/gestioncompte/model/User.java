package dz.esi.gestioncompte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

	@Size(max = 20)
	private String username;

	@Size(max = 50)
	@Email
	private String email;

	@Size(max = 120)
	private String password;
    
	@Temporal(TemporalType.DATE)
	private Date dateN;
	private String numTel;

	@Column
	@NotNull
	@Enumerated(EnumType.STRING)
	private Sex sex;

	private Long sin;

	@Column(length = 64)
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


	public User(String username, String email, Date dateN, String numTel, Long sin, String password, Sex sex ) {
		this.username = username;
		this.email = email;
		this.dateN=dateN;
		this.numTel=numTel;
		this.sex=sex;
		this.sin=sin;
		this.password = password;
	}

}
