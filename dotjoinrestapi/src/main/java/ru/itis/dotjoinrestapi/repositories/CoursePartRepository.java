package ru.itis.dotjoinrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.dotjoinrestapi.models.CoursePart;

import java.util.List;

public interface CoursePartRepository extends JpaRepository<CoursePart, Long> {

    @Query(nativeQuery = true, value = "select * from course_part " +
            "where course_id = :courseId " +
            "limit 1 offset :page")
    CoursePart getByCourseIdAndPage(Long courseId, Long page);

    List<CoursePart> getAllByCourseId(Long courseId);

    CoursePart getById(Long id);
}
