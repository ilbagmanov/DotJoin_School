package ru.itis.dotjoinrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.Course;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private Long courseId;
    private Long ownerId;
    private String title;
    private String description;
    private Long price;

    public static CourseDto from(Course course) {
        return CourseDto.builder()
                .courseId(course.getId())
                .ownerId(course.getOwnerId().getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .price(course.getPrice())
                .build();
    }

    public static List<CourseDto> from(List<Course> courses) {
        return courses.stream().map(CourseDto::from).collect(Collectors.toList());
    }

}
