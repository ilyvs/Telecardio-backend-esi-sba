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

    //Column(unique = true)
    private String email;

    //@Column(unique = true)
    private String numTelephone;

    private String activiteProf;

    //@Column(unique = true)
    private String numeroSecuriteSocial;

    private String groupeSanguin;

    @OneToOne(targetEntity = InformationBiometrique.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "biotrq_fk", referencedColumnName = "idenbi")
    private InformationBiometrique informationBiometrique;

    @OneToOne(targetEntity = AntecedentPersonnelle.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "antper_fk", referencedColumnName = "idenap")
    private AntecedentPersonnelle antecedentPersonnelle;

    @OneToOne(targetEntity = AntecedentMedicoCherigicaux.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "antmch_fk", referencedColumnName = "idenamc")
    private AntecedentMedicoCherigicaux antecedentMedicoCherigicaux;

    @OneToOne(targetEntity = SigneCardiaque.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "sgncrd_fk", referencedColumnName = "idensc")
    private SigneCardiaque signeCardiaque;

    @OneToMany(mappedBy = "infoPer", cascade = CascadeType.ALL)
    private List<PieceJoint> pieceJointList;


}
