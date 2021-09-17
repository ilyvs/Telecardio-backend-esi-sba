package dz.esi.rdv.proxy;


import dz.esi.rdv.DTO.PatientMail;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name="gestion-compte-service",url = "http://localhost:8083")
@LoadBalancerClient(name="gestion-compte-service")
public interface EmailProxy {

    @PostMapping("/api/auth/get-patient-email")
    PatientMail getPatientMail(@RequestParam("id") Long idPatient);

}
