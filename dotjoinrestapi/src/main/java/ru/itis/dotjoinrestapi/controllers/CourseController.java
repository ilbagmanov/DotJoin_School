package ru.itis.dotjoinrestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import ru.itis.dotjoinrestapi.auth.JwtUtil;
import ru.itis.dotjoinrestapi.dto.AccountDto;
import ru.itis.dotjoinrestapi.dto.CourseDto;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.Course;
import ru.itis.dotjoinrestapi.services.AccountService;
import ru.itis.dotjoinrestapi.services.CoursePartService;
import ru.itis.dotjoinrestapi.services.CourseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public class    CourseController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CoursePartService coursePartService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/mycourses")
    public ResponseEntity<?> getCoursesForUser(@RequestHeader("${jwt.header}") String token){
        AccountDto account = AccountDto.from(accountService.getAccountByEmail(jwtUtil.getUsername(token)));
        List<CourseDto> courses = account.getCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/buycourses")
    public ResponseEntity<?> getCanBuyCourses(@RequestHeader("${jwt.header}") String token){
        Account account = accountService.getAccountByEmail(jwtUtil.getUsername(token));
        List<CourseDto> list = courseService.getNotBuyCourses(account.getId());
        return ResponseEntity.ok(list);
    }

    @PostMapping("/course_buy/{course-id}")
    public ResponseEntity<?> buyCourse(@PathVariable("course-id") Long courseId, @RequestHeader("${jwt.header}") String token){
        Account account = accountService.getAccountByEmail(jwtUtil.getUsername(token));

        courseService.addCourse(courseId, account);

        Map<Object, Object> answer = new HashMap<>();
        answer.put("Status", "OK");
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/createdcourses")
    public ResponseEntity<?> getCreatedCourses(@RequestHeader("${jwt.header}") String token) {
        Account account = accountService.getAccountByEmail(jwtUtil.getUsername(token));
        Long id = account.getId();
        List<CourseDto> list = courseService.getCreatedCourses(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/course/{course-id}/{page}")
    public ResponseEntity<?> getCoursePart(@PathVariable("course-id") Long courseId,
                                           @PathVariable("page") Long page) {
        return ResponseEntity.ok(coursePartService.getCoursePartByPage(courseId, page));
    }

    @GetMapping("/course/{course-id}")
    public ResponseEntity<?> getCourse(@PathVariable("course-id") Long courseId, @RequestHeader("${jwt.header}") String token) {
        Account account = accountService.getAccountByEmail(jwtUtil.getUsername(token));
        return ResponseEntity.ok(courseService.getCourseDtoByIdAndOwnerId(courseId, account.getId()));
    }

    @GetMapping("/course/{course-id}/pages")
    public ResponseEntity<?> getCoursePages(@PathVariable("course-id") Long courseId) {
        return ResponseEntity.ok(coursePartService.getCoursePartes(courseId));
    }
}
