package dz.esi.rdv.repository;

import dz.esi.rdv.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a FROM Appointment a WHERE a.cas = 'approved' and a.patient_name= ?1")
    public Appointment findpatientapproved(String patient_name);

    @Query("SELECT a FROM Appointment a WHERE a.id_doc = ?1 ")
    public List<Appointment> findAllByIdDoc (Long DocId);

    @Query("SELECT a FROM Appointment a WHERE a.cas = 'refused' and a.patient_name= ?1")
    public Appointment findpatientrefused(String patient_name);

    @Query("SELECT a FROM Appointment a WHERE a.cas = 'approved' and a.doc_name= ?1")
    public Appointment finddocapproved(String doc_name);


    @Query("SELECT a FROM Appointment a WHERE a.cas = 'refused' and a.doc_name= ?1")
    public Appointment finddocrefused(String doc_name);

    @Query("SELECT a FROM Appointment a WHERE  a.doc_name= ?1")
    public Appointment finddoc(String doc_name);

    @Query("SELECT a FROM Appointment a WHERE  a.patient_name= ?1")
    public Appointment findpatient(String patient_name);

}


