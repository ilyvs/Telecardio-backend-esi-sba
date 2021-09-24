package dz.esi.dossiermedical.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AntecedentPersonnelle   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenap")
    private Long Id;

    private boolean fumer;

    private int nbrCigarettes;

    private boolean chique;

    private int nbrBoitesChique;

    private Boolean alcool;

    private String medicaments;

    /*@Transient
    private InformationPersonnelle informationPersonnelle;*/
}
