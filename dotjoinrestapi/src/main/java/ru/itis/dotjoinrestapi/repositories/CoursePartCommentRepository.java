package ru.itis.dotjoinrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.dotjoinrestapi.models.CoursePart;
import ru.itis.dotjoinrestapi.models.CoursePartComment;

import java.util.List;

public interface CoursePartCommentRepository extends JpaRepository<CoursePartComment, Long> {
    List<CoursePartComment> getAllByCoursePartId(CoursePart coursePartId);
}
