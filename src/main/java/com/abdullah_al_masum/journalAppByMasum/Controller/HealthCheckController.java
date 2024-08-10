package com.abdullah_al_masum.journalAppByMasum.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("health")
    public String HealthCheck(){
        return "My Journal App is running!";
    }
}
