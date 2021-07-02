package ru.itis.dotjoinrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAllUsers() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.getAccountById(id);
    }

    @Override
    public Account giveBanToAccount(Account account) {
        if (account.isBanned())
            account.setBanned(false);
        else
            account.setBanned(true);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.getByEmail(email).orElse(null);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }
}
