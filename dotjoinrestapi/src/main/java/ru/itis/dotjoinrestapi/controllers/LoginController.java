package ru.itis.dotjoinrestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dotjoinrestapi.auth.JwtTokenProvider;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.services.LoginServiceImpl;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController {

    @Autowired
    private LoginServiceImpl loginServiceImpl;
    @Autowired
    @Qualifier(value = "test1")
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody Account account) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword()));
            Account account1 = loginServiceImpl.getUserByEmail(account.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
            String token = jwtTokenProvider.createToken(account.getEmail(), account1.getRoles().get(0).toString());
            Map<Object, Object> response = new HashMap<>();
            response.put("email", account.getEmail());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        System.out.println(account);
        loginServiceImpl.save(account);
        return ResponseEntity.ok(account);
    }
}
