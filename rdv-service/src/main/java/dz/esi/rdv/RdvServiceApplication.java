package dz.esi.rdv;

import dz.esi.rdv.model.Appointment;
import dz.esi.rdv.repository.AppointmentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RdvServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RdvServiceApplication.class, args);
    }

}
