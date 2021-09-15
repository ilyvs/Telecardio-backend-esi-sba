package dz.esi.rdv.proxy;

import dz.esi.rdv.DTO.DoctorCHU;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="gestion-compte-service",url = "http://localhost:8083")
//@LoadBalancerClient(name="gestion-compte-service")
public interface DoctorProxy {

    @PostMapping("/api/auth/get-all-doctors")
    List<DoctorCHU> getAllDoctors();


}
