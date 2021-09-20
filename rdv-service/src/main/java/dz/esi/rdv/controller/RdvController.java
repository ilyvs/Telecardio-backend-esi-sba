package dz.esi.rdv.controller;

import dz.esi.rdv.DTO.DoctorCHU;
import dz.esi.rdv.DTO.PatientMail;
import dz.esi.rdv.model.Appointment;
import dz.esi.rdv.proxy.EmailProxy;
import dz.esi.rdv.repository.AppointmentRepository;
import dz.esi.rdv.service.RdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class RdvController {


    @Autowired
    private RdvService rdvService;

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProxy emailProxy;

    /** TODO : Patient Methods */

    @GetMapping("/get-all-doctors")
    public List<DoctorCHU> getAllDoctors() {
        return rdvService.getAllDoctors();
    }

/*
    @PostMapping("/add-appointment")
    public boolean addAppointment(@RequestBody Appointment newAppointment) {
        List<Appointment> allAppointments = appointmentRepo.findAll();
        for ( Appointment appointment : allAppointments) {
            if (( appointment.getDate().compareTo(newAppointment.getDate()) == 0 )
                    && appointment.getCas().equals("approved")
                    && newAppointment.getDate().compareTo(new Date()) < 0){
                return false;
            }
        }
        appointmentRepo.save(newAppointment);
        return true;
    }*/

    @PostMapping("/add-appointment")
    public int addAppointment(@RequestBody Appointment newAppointment) {
        List<Appointment> allAppointments = appointmentRepo.findAll();
        Date currentdate = new Date();
        for ( Appointment appointment : allAppointments) {
            if (( appointment.getDate().compareTo(newAppointment.getDate()) == 0 ) && appointment.getCas().equals("approved")){
                return 1;
            }
        }
        if (newAppointment.getDate().compareTo(currentdate) < 0) { return 2;  }{


            appointmentRepo.save(newAppointment);
            return 3;

        }
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


    private void sendapproveEmail(String mail )
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = mail;
        String fromAddress = "chusba022@gmail.com\n";
        String senderName = "CHU tele cardio <\n";
        String subject = "Your Appointment is approved ";
        String content = "Dear patient,<br>"
                + "Thank you,<br>"
                + "CHU SBA.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(content, true);
        mailSender.send(message);

    }


    private void sendrefusedEmail(String mail)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = mail;
        String fromAddress = "chusba022@gmail.com\n";
        String senderName = "CHU tele cardio <\n";
        String subject = "Your Appointment is approved ";
        String content = "Dear patient,<br>"
                + "we are sorry to informe you that your appointment has been refused due to the doctors wishes ,<br>"
                + "please add another appointment in a different time " +
                 "or select another doctor "
                + "CHU SBA.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        helper.setText(content, true);
        mailSender.send(message);

    }

    @PutMapping("/approve-rdv/{id}")
    public void  approverdv( @PathVariable Long id) throws MessagingException, UnsupportedEncodingException {
        Appointment appointment = appointmentRepo.findById(id).orElse(null);
        appointment.setCas("approved");
        appointment.setAppointment_id(id);
        appointmentRepo.save(appointment);
        System.out.println("qsljdqsjkdqsd");
        PatientMail patientMail = emailProxy.getPatientMail(appointment.getPatient_id());
        System.out.println("sdsds"+patientMail.getEmail());
        sendapproveEmail(patientMail.getEmail());
    }

    @PutMapping("/refuse-rdv/{id}")
    public void resfuserdv( @PathVariable Long id) throws MessagingException, UnsupportedEncodingException {
        Appointment appointment = appointmentRepo.findById(id).orElse(null);
        PatientMail patientMail = emailProxy.getPatientMail(appointment.getPatient_id());
        sendrefusedEmail(patientMail.getEmail());
        appointmentRepo.delete(appointment);


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







    /** TODO : Nurse Methods */

    @GetMapping("/consult-doctor-approved-appointments")
    public List<Appointment> consultDocApprovedAppointments(@RequestParam("doctorName") String doctorName) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        List<Appointment> allAppointments = appointmentRepo.findAll();
        for ( Appointment appointment : allAppointments)
            if (appointment.getDoc_name().equals(doctorName))
                if(appointment.getCas().equals("approved"))
                    doctorAppointments.add(appointment);
        return doctorAppointments;
    }

    @GetMapping("/consult-doctor-new-appointments")
    public List<Appointment> consultDocNewAppointments(@RequestParam("doctorName") String doctorName) {
        List<Appointment> doctorAppointments = new ArrayList<>();
        List<Appointment> allAppointments = appointmentRepo.findAll();
        for ( Appointment appointment : allAppointments)
            if (appointment.getDoc_name().equals(doctorName))
                if(appointment.getCas().equals(""))
                    doctorAppointments.add(appointment);
        return doctorAppointments;
    }

}
