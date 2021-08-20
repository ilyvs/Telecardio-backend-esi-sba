package dz.esi.dossiermedical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DossierMedicalServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DossierMedicalServiceApplication.class, args);
    }

}
