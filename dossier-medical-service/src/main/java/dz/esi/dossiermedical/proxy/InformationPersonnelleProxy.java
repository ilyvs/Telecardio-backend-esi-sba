package dz.esi.dossiermedical.proxy;

import dz.esi.dossiermedical.DTO.User;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="gestion-compte-service")
@LoadBalancerClient(name="gestion-compte-service",configuration = LBConfiguration.class)
public interface InformationPersonnelleProxy {

    @GetMapping("/**/{id}")
    User getUserInformation(@PathVariable("id") Long idD);

}
