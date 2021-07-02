package ru.itis.dotjoinrestapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursePartComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_part_id")
    private CoursePart coursePartId;

    @ManyToOne
    @JoinColumn(name = "comment_owner_id")
    private Account commentOwnerId;

    private String ownerEmail;

    private String text;

    private Timestamp commentData;
}
