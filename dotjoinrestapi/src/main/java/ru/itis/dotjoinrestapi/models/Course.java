package ru.itis.dotjoinrestapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Account ownerId;

    @Column(unique = true)
    private String title;

    private String description;
    private String photoUrl;
    private Long price;
    private Timestamp startDate;

    @ManyToMany
    @JoinTable(name = "account_course",
    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "bought_user_id", referencedColumnName = "id"))
    private List<Account> accounts;
}
