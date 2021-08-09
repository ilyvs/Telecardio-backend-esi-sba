package com.bezkoder.springjwt.models;

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
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
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

	@Column(name = "verification_code", length = 64)
	private String verificationCode;

	private boolean enabled;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}




	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}


	public String getNumTel(){ return numTel;}
	public void setNumTel(String numTel){this.numTel=numTel;}

	public Sex getSex(){ return sex;}
	public void setSex(Sex sex){this.sex=sex;}

	public Long getSin(){ return sin;}
	public void setSin(Long sin){this.sin=sin;}

	public String getVerificationCode(){return verificationCode;}
	public void setVerificationCode(String verificationCode){this.verificationCode=verificationCode;}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
