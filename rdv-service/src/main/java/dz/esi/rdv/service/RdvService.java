package dz.esi.rdv.service;


import dz.esi.rdv.DTO.DoctorCHU;
import dz.esi.rdv.model.Appointment;
import dz.esi.rdv.proxy.DoctorProxy;
import dz.esi.rdv.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RdvService {

    @Autowired
    private AppointmentRepository appointmentRepo;

    @Autowired
    DoctorProxy doctorProxy;


    public List<DoctorCHU> getAllDoctors(){
            List<DoctorCHU>  doctorCHUList = doctorProxy.getAllDoctors();
        return doctorCHUList;
    }

    public List<Appointment> getAllMyAppointment(@PathVariable("id")Long docId) {
        return appointmentRepo.findAllByIdDoc(docId);
    }


   
}
