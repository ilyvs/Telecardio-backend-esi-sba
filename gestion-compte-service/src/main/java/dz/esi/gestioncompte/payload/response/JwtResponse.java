package dz.esi.gestioncompte.payload.response;

import dz.esi.gestioncompte.model.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String nom;
	private String prenom;
	private Date dateNaissance;
	private String lieuNaissance;
	private String email;
	private Sex sex;
	private String numTelephone;
	private String activiteProf;
	private Long numeroSecuriteSocial;
	private String groupeSanguin;
	private List<String> roles;


	public JwtResponse(String token, Long id, String username , String nom, String prenom, Date dateNaissance, String lieuNaissance,
					   String email, Sex sex , String numTelephone,
					   String activiteProf, Long numeroSecuriteSocial, String groupeSanguin, List<String> roles) {
		this.token = token;
		this.id = id;
		this.username = username;
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.lieuNaissance = lieuNaissance;
		this.email = email;
		this.sex = sex;
		this.numTelephone = numTelephone;
		this.activiteProf = activiteProf;
		this.numeroSecuriteSocial = numeroSecuriteSocial;
		this.groupeSanguin = groupeSanguin;
		this.roles = roles;
	}
}
