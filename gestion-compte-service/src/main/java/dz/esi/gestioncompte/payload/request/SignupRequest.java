package dz.esi.gestioncompte.payload.request;

import dz.esi.gestioncompte.model.Sex;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
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




}
