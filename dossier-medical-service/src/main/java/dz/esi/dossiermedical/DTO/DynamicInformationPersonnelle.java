package dz.esi.dossiermedical.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DynamicInformationPersonnelle {

    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String lieuNaissance;
    private String sex;
    private String adresse;
    private String email;
    private String numTelephone;
    private String activiteProf;
    private Long numeroSecuriteSocial;
    private String groupeSanguin;
}
