package ru.itis.dotjoinrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.AccountRole;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {

    private Long id;

    private String name;
    private String surname;

    private String email;

    private String password;

    private List<AccountRole> roles;

    private List<CourseDto> courses;

    public static AccountDto from(Account account) {
        AccountDto result = AccountDto.builder()
                .id(account.getId())
                .name(account.getName())
                .surname(account.getSurname())
                .email(account.getEmail())
                .password(account.getPassword())
                .roles(account.getRoles())
                .build();

        if (account.getCourses() != null) {
            result.setCourses(account.getCourses().stream().map(CourseDto::from).collect(Collectors.toList()));
        }
        return result;
    }

    public static List<AccountDto> from(List<Account> teachers) {
        return teachers.stream().map(AccountDto::from).collect(Collectors.toList());
    }

}
