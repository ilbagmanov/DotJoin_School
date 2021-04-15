package ru.itis.dotjoinrestapi.services;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dotjoinrestapi.auth.AccountUserDetails;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.AccountRole;
import ru.itis.dotjoinrestapi.repositories.AccountRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class LoginServiceImpl implements UserDetailsService, LoginService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        val account = accountRepository.getByEmail(username).orElse(Account.builder().build());

        if(account == null){
            throw new UsernameNotFoundException("Not found account");
        }

        return new AccountUserDetails(account);
    }

    public Account save(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRoles(Collections.singletonList(new AccountRole(1L,"ROLE_USER")));
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> getUserByEmail(String email) {
        return accountRepository.getByEmail(email);
    }
}
