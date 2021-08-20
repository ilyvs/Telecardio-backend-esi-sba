package dz.esi.gestioncompte.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "social_num", uniqueConstraints = { @UniqueConstraint(columnNames = "id"), @UniqueConstraint(columnNames = "sin") })

public class SocialNum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long sin;


}
