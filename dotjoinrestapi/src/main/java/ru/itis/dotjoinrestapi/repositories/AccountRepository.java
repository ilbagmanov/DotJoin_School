package ru.itis.dotjoinrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.dotjoinrestapi.models.Account;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> getByEmail(final String email);
    Optional<Account> getAccountById(final Long id);
}
