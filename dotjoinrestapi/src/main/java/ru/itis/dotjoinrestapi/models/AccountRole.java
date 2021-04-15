package ru.itis.dotjoinrestapi.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String role;

    public AccountRole(String name){
        this.role = name;
    }
    public AccountRole(Long id, String name){
        this.id = id;
        this.role = name;
    }
}
