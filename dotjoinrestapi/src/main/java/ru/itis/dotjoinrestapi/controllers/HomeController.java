package ru.itis.dotjoinrestapi.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class HomeController {

    @GetMapping("/home")
    public ResponseEntity<?> getHome() {
        return ResponseEntity.ok("{\"Title\":\"successfully\"}");
    }

    @GetMapping("/homeAdmin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<?> getHomeAdmin() {
        return ResponseEntity.ok("Hello, admin");
    }
}
