package ru.itis.dotjoinrestapi.dto;

import lombok.Builder;
import lombok.Data;
import ru.itis.dotjoinrestapi.models.Course;
import ru.itis.dotjoinrestapi.models.CoursePart;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class CoursePartDto {

    private Long id;
    private Long courseId;
    private String title;
    private String text;

    public static CoursePartDto from(CoursePart course) {
        return CoursePartDto.builder()
                .courseId(course.getCourseId())
                .id(course.getId())
                .title(course.getTitle())
                .text(course.getText())
                .build();
    }

    public static List<CoursePartDto> from(List<CoursePart> courses) {
        return courses.stream().map(CoursePartDto::from).collect(Collectors.toList());
    }
}
