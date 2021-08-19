package dz.esi.gestioncompte.payload.request;

import dz.esi.gestioncompte.model.Sex;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
    @Temporal(TemporalType.DATE)
    private Date dateN;
    @NotNull
    private String numTel;

    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @NotNull
    private Long sin;

    private Set<String> role;

    private String verificationCode;

    private boolean enabled;


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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public String getVerificationCode(){return verificationCode;}
    public void setVerificationCode(String verificationCode){this.verificationCode=verificationCode;}

    public Boolean getEnabled(){return enabled;}
    public void setEnabled(Boolean enabled){this.enabled=enabled;}


}
