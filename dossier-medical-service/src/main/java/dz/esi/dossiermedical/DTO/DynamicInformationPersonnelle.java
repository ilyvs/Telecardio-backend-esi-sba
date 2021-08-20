package dz.esi.dossiermedical.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationPersonnelle {

    private Long userId;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String lieuNaissance;
    private String gendre;
    private String adresse;
    private String email;

}
