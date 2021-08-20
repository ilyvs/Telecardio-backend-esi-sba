package dz.esi.dossiermedical.proxy;

import dz.esi.dossiermedical.DTO.DynamicInformationPersonnelle;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="gestion-compte-service")
@LoadBalancerClient(name="gestion-compte-service",configuration = LBConfiguration.class)
public interface InformationPersonnelleProxy {

    @PostMapping("/api/auth/signin")
    DynamicInformationPersonnelle getInformationPersonnelle(@RequestBody LoginRequest loginRequest);

}
