package dz.esi.gestioncompte.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class VersionController {

    @Value("${healthcheck.version}")
    private String version;


    @GetMapping("/healthcheck")
    public String getHealthcheck() {
        return this.version;
    }


}
