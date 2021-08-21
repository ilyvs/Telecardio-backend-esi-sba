package dz.esi.dossiermedical.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PatientDossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenpt")
    private Long id;

    @OneToOne(targetEntity = InformationPersonnelle.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "infper_fk", referencedColumnName = "idenip")
    private InformationPersonnelle informationPersonnelle;

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


}
