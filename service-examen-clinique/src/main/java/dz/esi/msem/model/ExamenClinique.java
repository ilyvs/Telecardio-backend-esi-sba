package dz.esi.msem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExamenClinique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idened")
    private Long id;

    @OneToOne(targetEntity = Pouls.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pouls_fk", referencedColumnName = "idenpl")
    private Pouls pouls;

    @OneToOne(targetEntity = SigneVitaux.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "signeVitaux_fk", referencedColumnName = "idensv")
    private SigneVitaux signeVitaux;

    @OneToOne(targetEntity = Veine.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "veine_fk", referencedColumnName = "idenve")
    private Veine veine;

    @OneToOne(targetEntity = Thorax.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "thorax_fk", referencedColumnName = "identh")
    private Thorax thorax;

    @OneToOne(targetEntity = Poumons.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "poumons_fk", referencedColumnName = "idenpo")
    private Poumons poumons;

    @OneToOne(targetEntity = Abdominal.class,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "abdominal_fk", referencedColumnName = "idenab")
    private Abdominal abdominal;

    private String ordonnance;

    private String orientation;

    private String evacuation;

    private String certificatMedical;


}