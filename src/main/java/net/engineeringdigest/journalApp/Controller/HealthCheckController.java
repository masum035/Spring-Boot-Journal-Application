package net.engineeringdigest.journalApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("health")
    public String HealthCheck(){
        return "My Journal App is running!";
    }
}
