package ru.itis.dotjoinrestapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dotjoinrestapi.models.Account;

@RestController
@CrossOrigin("http://localhost:4200")
public class WelcomeController {

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcome() {
        return ResponseEntity.ok("{\"isAuthorized\": \"false\"}");
    }
}
