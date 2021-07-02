package ru.itis.dotjoinrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.Course;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getCourseById(Long id);

    @Query(nativeQuery = true, value = "select * from course " +
            "where id not in (select course_id from account_course " +
            "where bought_user_id = :userId)")
    List<Course> getCoursesNotBuyWithAccount(Long userId);

    List<Course> getCoursesByOwnerId(Account ownerId);

    @Query(nativeQuery = true, value = "select * from account_course\n" +
            "inner join course on account_course.course_id = course.id\n" +
            "where course_id = :courseId and bought_user_id = :ownerId")
    Course getCourseByIdAndOwnerId(Long courseId, Long ownerId);
}
