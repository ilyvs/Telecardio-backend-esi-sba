package dz.esi.gestioncompte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "social_num", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), @UniqueConstraint(columnNames = "numero_securite_social") })

public class SocialNum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_securite_social")
    private Long numeroSecuriteSocial;


}
