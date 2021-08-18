package dz.esi.examenclinique.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Veine {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenve")
    private Long id;

    private String veinesPeripheriques;

    private String veinesJugulaires;

    private String veinesDuCou;

    @Transient
    private ExamenClinique examenClinique;

}
