package ru.itis.dotjoinrestapi.services;

import ru.itis.dotjoinrestapi.dto.CoursePartDto;
import ru.itis.dotjoinrestapi.models.CoursePart;

import java.util.List;

public interface CoursePartService {
    CoursePartDto save(CoursePartDto dto);

    CoursePartDto getCoursePartByPage(Long courseId, Long page);

    List<CoursePartDto> getCoursePartes(Long courseId);

    CoursePart getCoursePartById(Long coursePartId);
}
