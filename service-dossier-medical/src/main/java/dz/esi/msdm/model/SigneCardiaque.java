package dz.esi.msdm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SigneCardiaque {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idensc")
    private Long Id;

    private boolean geneThoracique;

    private boolean dyspnée;

    private boolean asthenie;

    private boolean fatigue;

    private boolean palpitations;

    private boolean lipothymies;

    private boolean evanouissement;

    private boolean syncopeOedemes;

    @Transient
    private InformationPersonnelle informationPersonnelle;
}
