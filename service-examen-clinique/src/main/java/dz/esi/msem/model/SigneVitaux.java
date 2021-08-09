package dz.esi.msem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SigneVitaux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idensv")
    private Long id;

    private String pressionArterielle;

    private String FrequenceEtRythmeCardiaques;

    private String frequenceRespiratoire;

    private int temperature;

    private String spO2;

    @Transient
    private ExamenClinique examenClinique;
}
