package ru.itis.dotjoinrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.Course;
import ru.itis.dotjoinrestapi.models.CoursePart;
import ru.itis.dotjoinrestapi.models.CoursePartComment;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoursePartCommentDto {

    Long id;
    CoursePart coursePartId;
    AccountDto commentOwnerId;
    String ownerEmail;
    String text;
    private Timestamp commentData;

    public static CoursePartCommentDto from(CoursePartComment course) {
        return CoursePartCommentDto.builder()
                .id(course.getId())
                .coursePartId(course.getCoursePartId())
                .commentOwnerId(AccountDto.from(course.getCommentOwnerId()))
                .ownerEmail(course.getOwnerEmail())
                .text(course.getText())
                .commentData(course.getCommentData())
                .build();
    }

    public static List<CoursePartCommentDto> from(List<CoursePartComment> courses) {
        return courses.stream().map(CoursePartCommentDto::from).collect(Collectors.toList());
    }
}
