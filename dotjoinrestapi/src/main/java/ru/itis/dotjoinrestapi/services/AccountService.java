package ru.itis.dotjoinrestapi.services;

import ru.itis.dotjoinrestapi.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAllUsers();
    Optional<Account> getAccountById(Long id);
    Account giveBanToAccount(Account account);
}
