package ru.itis.dotjoinrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dotjoinrestapi.dto.CourseDto;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.repositories.CourseRepository;

import java.util.List;

public interface CourseService {
    CourseDto createCourse (CourseDto courseDto);
    Account addCourse(Long courseId, Account account);

    List<CourseDto> getAllCourses();

    List<CourseDto> getNotBuyCourses(Long userId);

    CourseDto getCourseDtoById(Long courseId);

    CourseDto getCourseDtoByIdAndOwnerId(Long courseId, Long onwerId);

    List<CourseDto> getCreatedCourses(Long userId);
}
