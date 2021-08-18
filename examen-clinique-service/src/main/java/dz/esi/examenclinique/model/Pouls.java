package dz.esi.examenclinique.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pouls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenpl")
    private Long id;

    private String poulsPeripheriques;

    private String poulsCarotidiens;

    @Transient
    private ExamenClinique examenClinique;

}
