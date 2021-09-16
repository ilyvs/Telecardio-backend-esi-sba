package dz.esi.examenclinique.proxy;

import dz.esi.examenclinique.DTO.Nss;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name="gestion-compte-service")
@LoadBalancerClient(name="gestion-compte-service",configuration = LBConfiguration.class)
public interface NssProxy {

    @PostMapping("/get-all-nss")
    List<Nss> getAllNss();


}
