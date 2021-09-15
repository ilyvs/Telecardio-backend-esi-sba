package dz.esi.rdv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Appointment  {

	sout
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointment_id;
	private Date date;
	private String time;
	private String notes;
	private Long id_doc;
	private String doc_name;
	private Long patient_id;
	private String cas;

	public Long getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(long appointment_id) {
		this.appointment_id = appointment_id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCas() { return cas; }
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public  void setCas(String cas) {
		this.cas = cas;
	}



}
