package dz.esi.dossiermedical.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InformationPersonnelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenip")
    private Long id;

    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String lieuNaissance;
    private String gendre;
    private String adresse;
    private String email;
    private String numTelephone;
    private String activiteProf;
    private String numeroSecuriteSocial;
    private String groupeSanguin;

    public InformationPersonnelle(String nom, String prenom, Date dateNaissance,
                                  String lieuNaissance, String gendre, String adresse,
                                  String email, String numTelephone, String activiteProf,
                                  String numeroSecuriteSocial, String groupeSanguin) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.gendre = gendre;
        this.adresse = adresse;
        this.email = email;
        this.numTelephone = numTelephone;
        this.activiteProf = activiteProf;
        this.numeroSecuriteSocial = numeroSecuriteSocial;
        this.groupeSanguin = groupeSanguin;
    }

}
