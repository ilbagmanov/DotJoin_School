package ru.itis.dotjoinrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dotjoinrestapi.dto.CoursePartDto;
import ru.itis.dotjoinrestapi.models.Course;
import ru.itis.dotjoinrestapi.models.CoursePart;
import ru.itis.dotjoinrestapi.repositories.CoursePartRepository;

import java.util.List;

@Service
public class CoursePartServiceImpl implements CoursePartService {

    @Autowired
    private CoursePartRepository coursePartRepository;

    @Override
    public CoursePartDto save(CoursePartDto dto) {
        CoursePart course = CoursePart.builder()
                .courseId(dto.getCourseId())
                .title(dto.getTitle())
                .id(dto.getId())
                .text(dto.getText())
                .build();
        return CoursePartDto.from(coursePartRepository.save(course));
    }

    @Override
    public CoursePartDto getCoursePartByPage (Long courseId, Long page) {
        return CoursePartDto.from(coursePartRepository.getByCourseIdAndPage(courseId, page));
    }

    @Override
    public List<CoursePartDto> getCoursePartes(Long courseId) {
        return CoursePartDto.from(coursePartRepository.getAllByCourseId(courseId));
    }

    @Override
    public CoursePart getCoursePartById(Long coursePartId) {
        return coursePartRepository.getById(coursePartId);
    }


}
