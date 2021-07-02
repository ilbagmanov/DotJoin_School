package ru.itis.dotjoinrestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dotjoinrestapi.auth.JwtUtil;
import ru.itis.dotjoinrestapi.dto.CourseDto;
import ru.itis.dotjoinrestapi.dto.CoursePartDto;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.Course;
import ru.itis.dotjoinrestapi.services.AccountService;
import ru.itis.dotjoinrestapi.services.CoursePartService;
import ru.itis.dotjoinrestapi.services.CourseService;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:4200")
public class CourseCreateController {

    @Autowired
    private CoursePartService coursePartService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/course_create")
    public ResponseEntity<?> createCourse(@RequestBody CourseDto courseDto, @RequestHeader(name = "Authorization") String token) {
        courseDto.setOwnerId(accountService.getAccountByEmail(jwtUtil.getUsername(token)).getId());
        courseService.createCourse(courseDto);

        Map<Object, Object> answer = new HashMap<>();
        answer.put("Status", "Course added");
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/course/{course-id}/add")
    public ResponseEntity<?> addPageToCourse(@PathVariable("course-id") Long courseId, @RequestBody CoursePartDto dto, @RequestHeader(name = "Authorization") String token) {
        Account account = accountService.getAccountByEmail(jwtUtil.getUsername(token));
        CourseDto course = courseService.getCourseDtoById(courseId);
        if (!course.getOwnerId().equals(account.getId()))
            return ResponseEntity.status(403).body(Collections.singletonMap("Problem", "It is not your course"));
        dto.setCourseId(courseId);
        coursePartService.save(dto);

        return ResponseEntity.ok(Collections.singletonMap("Status", "Successfully"));
    }

}
