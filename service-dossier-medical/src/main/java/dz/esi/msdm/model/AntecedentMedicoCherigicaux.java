package dz.esi.msdm.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class AntecedentMedicoCherigicaux  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenamc")
    private Long Id;

    private String medicaux;

    private String chirurgicaux;

    private String allergique;

    private String familiaux;

    private String toxiques;

    @Transient
    private InformationPersonnelle informationPersonnelle;
}
