package ru.itis.dotjoinrestapi.services;

import ru.itis.dotjoinrestapi.models.Account;

import java.util.Optional;

public interface LoginService {
    Optional<Account> getUserByEmail(String email);
}
