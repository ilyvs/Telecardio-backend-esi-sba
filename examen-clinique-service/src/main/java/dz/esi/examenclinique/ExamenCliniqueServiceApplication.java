package dz.esi.examenclinique;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ExamenCliniqueServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamenCliniqueServiceApplication.class, args);
    }

}
