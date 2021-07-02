package ru.itis.dotjoinrestapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dotjoinrestapi.auth.JwtUtil;
import ru.itis.dotjoinrestapi.dto.AccountDto;
import ru.itis.dotjoinrestapi.dto.CoursePartCommentDto;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.CoursePart;
import ru.itis.dotjoinrestapi.services.AccountService;
import ru.itis.dotjoinrestapi.services.CoursePartCommentService;
import ru.itis.dotjoinrestapi.services.CoursePartService;

@RestController
public class CoursePartCommentController {

    @Autowired
    private CoursePartCommentService coursePartCommentService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CoursePartService coursePartService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/pageid/{page-id}/comments")
    public ResponseEntity<?> getPageComments(@PathVariable("page-id") Long pageId) {
        return ResponseEntity.ok(coursePartCommentService.getPageComment(pageId));
    }

    @PostMapping("/pageid/{page-id}/comments/add")
    public ResponseEntity<?> addComment(@PathVariable("page-id") Long pageId,
                                        @RequestHeader("${jwt.header}") String token,
                                        @RequestBody CoursePartCommentDto dto){
        Account account = accountService.getAccountByEmail(jwtUtil.getUsername(token));
        CoursePart coursePart = coursePartService.getCoursePartById(pageId);
        dto.setCommentOwnerId(AccountDto.from(account));
        dto.setOwnerEmail(jwtUtil.getUsername(token));
        dto.setCoursePartId(coursePart);
        return ResponseEntity.ok(coursePartCommentService.save(dto));
    }
}
