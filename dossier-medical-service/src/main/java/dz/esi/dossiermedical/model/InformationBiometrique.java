package dz.esi.dossiermedical.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InformationBiometrique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenbi")
    private Long id;

    private Double taille;

    private Double poids;

    private Double imc;

    @Transient
    private InformationPersonnelle informationPersonnelle;
}


















