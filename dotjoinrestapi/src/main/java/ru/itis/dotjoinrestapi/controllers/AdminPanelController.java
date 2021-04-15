package ru.itis.dotjoinrestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.services.AccountService;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class AdminPanelController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Account> getUsersInJson(){
        return accountService.getAllUsers();
    }

    @PostMapping("/users/ban/{account_id}/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> giveBan(@PathVariable("account_id") Long accountId){
        Account account = accountService.getAccountById(accountId).orElseThrow(() -> new IllegalArgumentException(""));
        account = accountService.giveBanToAccount(account);

        if(account.isBanned())
            return ResponseEntity.ok("{\"result\":\"OK\"}");
        else
            return ResponseEntity.ok("{\"result\":\"OK\"}");
    }
}
