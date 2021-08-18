package dz.esi.examenclinique.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Poumons {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenpo")
    private Long id;

    private String percussion;

    private String palpation;

    private String auscultation;

    @Transient
    private ExamenClinique examenClinique;
}
