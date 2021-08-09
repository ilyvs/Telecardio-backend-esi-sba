package dz.esi.msdm.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pjoint")
public class PieceJoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenpj")
    private Long id;

    @Column(name = "nompj", nullable = false)
    private String nom;

    /*@Column(name = "pathpj", nullable = false)
    private String path;

    @Column(name = "srcpj", nullable = false)
    private String source;

    @Column(name = "datepj", nullable = false)
    private Date date;

    @Column(name = "dscrpj", nullable = false)
    private String description;*/

    @ManyToOne(targetEntity = InformationPersonnelle.class,cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "infper_fk", referencedColumnName = "idenip")
    private InformationPersonnelle infoPer;

}
