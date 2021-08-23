package dz.esi.gestioncompte.security.services;

import dz.esi.gestioncompte.model.Sex;
import dz.esi.gestioncompte.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private User user;

	public UserDetailsImpl(User user) {
		this.user = user;
	}

	private Long id;

	private String username;

	private String nom;

	private String prenom;

	@Temporal(TemporalType.DATE)
	private Date dateNaissance;

	private String lieuNaissance;

	@Enumerated(EnumType.STRING)
	private Sex sex;

	private String email;

	private String numTelephone;

	private String activiteProf;

	private Long numeroSecuriteSocial;

	private String groupeSanguin;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username ,String nom, String prenom, Date dateNaissance, String lieuNaissance, Sex sex,  String email,
						   String numTelephone, String activiteProf, Long numeroSecuriteSocial, String groupeSanguin, String password ,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
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
		this.authorities = authorities;
	}




	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());

		return new UserDetailsImpl(
				user.getId(), 
				user.getUsername(),
				user.getNom(),
				user.getPrenom(),
				user.getDateNaissance(),
				user.getLieuNaissance(),
				user.getSex(),
				user.getEmail(),
				user.getNumTelephone(),
				user.getActiviteProf(),
				user.getNumeroSecuriteSocial(),
				user.getGroupeSanguin(),
				user.getPassword(),
				authorities);
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public Sex getSex() {
		return sex;
	}

	public String getNumTelephone() {
		return numTelephone;
	}

	public String getActiviteProf() {
		return activiteProf;
	}

	public Long getNumeroSecuriteSocial() {
		return numeroSecuriteSocial;
	}

	public String getGroupeSanguin() {
		return groupeSanguin;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
}
