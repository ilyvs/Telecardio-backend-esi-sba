package dz.esi.rdv.controller;

import dz.esi.rdv.DTO.DoctorCHU;
import dz.esi.rdv.model.Appointment;
import dz.esi.rdv.repository.AppointmentRepository;
import dz.esi.rdv.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class RdvController {


    @Autowired
    private RdvService rdvService;

    @Autowired
    private AppointmentRepository appointmentRepo;


    /** TODO : Patient Methods */

    @PostMapping("/get-all-doctors")
    public List<DoctorCHU> getAllDoctors() {
        return rdvService.getAllDoctors();
    }


    @PostMapping("/add-appointment")
    public boolean addAppointment(@RequestBody Appointment newAppointment) {
        List<Appointment> allAppointments = appointmentRepo.findAll();
        for ( Appointment appointment : allAppointments) {
            if (( appointment.getDate().compareTo(newAppointment.getDate()) == 0 ) && appointment.getCas().equals("approved")){
                return false;
            }
        }
        appointmentRepo.save(newAppointment);
        return true;
    }


    @PutMapping("/edit-appointment")
    public Appointment editAppointment(@RequestBody Appointment newAppointment) {

        return appointmentRepo.findById(newAppointment.getAppointment_id())
                .map(Appointment -> {
                    Appointment.setAppointment_id(newAppointment.getAppointment_id());
                    Appointment.setDate(newAppointment.getDate());
                    Appointment.setNotes(newAppointment.getNotes());
                    Appointment.setId_doc(newAppointment.getId_doc());
                    Appointment.setDoc_name(newAppointment.getDoc_name());
                    Appointment.setPatient_id(newAppointment.getPatient_id());
                    Appointment.setCas(newAppointment.getCas());
                    return appointmentRepo.save(Appointment);
                })
                .orElseGet(() -> {
                    newAppointment.setAppointment_id(newAppointment.getAppointment_id());
                    return appointmentRepo.save(newAppointment);
                });
    }


    @GetMapping("/consult-my-appointments/{id}")
    public List<Appointment> consultMyAppointments(@PathVariable Long id) {
        List<Appointment> myAppointments = new ArrayList<>();
        List<Appointment> allAppointments = appointmentRepo.findAll();
        for ( Appointment appointment : allAppointments)
            if (appointment.getPatient_id() == id)
                myAppointments.add(appointment);

        return myAppointments;
    }

    /** TODO : Doctor Methods */
    @GetMapping("/get-new-appointment/{id}")
    public List<Appointment> getNewAppointment(@PathVariable("id")Long docId) {
        List<Appointment> appointments = rdvService.getAllMyAppointment(docId);
        List<Appointment> newAppointments = new ArrayList<>();
        for (Appointment appointment : appointments)
            if(appointment.getCas().equals(""))
                newAppointments.add(appointment);
        return newAppointments;
    }

    @PutMapping("/approve-rdv/{id}")
    public void  approverdv( @PathVariable Long id) {
        Appointment appointment = appointmentRepo.findById(id).orElse(null);
        appointment.setCas("approved");
        appointment.setAppointment_id(id);
        appointmentRepo.save(appointment);
    }

    @PutMapping("/refuse-rdv/{id}")
    public void resfuserdv( @PathVariable Long id) {
        appointmentRepo.delete(appointmentRepo.findById(id).orElse(null));
    }

    @GetMapping("/get-approved-appointment/{id}")
    public List<Appointment> getApprovedAppointment(@PathVariable("id")Long docId) {
        List<Appointment> appointments = rdvService.getAllMyAppointment(docId);
        List<Appointment> acceptedAppointments = new ArrayList<>();
        for (Appointment appointment : appointments)
            if(appointment.getCas().equals("approved"))
                acceptedAppointments.add(appointment);
        return acceptedAppointments;
    }

}
