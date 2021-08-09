package dz.esi.msem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Thorax {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identh")
    private Long id;

    private String inspection;

    private String palpation;

    @Transient
    private ExamenClinique examenClinique;
}
