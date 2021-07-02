package ru.itis.dotjoinrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dotjoinrestapi.dto.CoursePartCommentDto;
import ru.itis.dotjoinrestapi.models.CoursePartComment;
import ru.itis.dotjoinrestapi.repositories.AccountRepository;
import ru.itis.dotjoinrestapi.repositories.CoursePartCommentRepository;
import ru.itis.dotjoinrestapi.repositories.CoursePartRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CoursePartCommentServiceImpl implements CoursePartCommentService {

    @Autowired
    private CoursePartRepository coursePartRepository;

    @Autowired
    private CoursePartCommentRepository coursePartCommentRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<CoursePartCommentDto> getPageComment(Long id) {
        return CoursePartCommentDto.from(coursePartCommentRepository.getAllByCoursePartId(coursePartRepository.getById(id)));
    }

    @Override
    public CoursePartCommentDto save(CoursePartCommentDto dto) {
        CoursePartComment save = CoursePartComment.builder()
                .commentData(new Timestamp(System.currentTimeMillis()))
                .coursePartId(dto.getCoursePartId())
                .ownerEmail(dto.getOwnerEmail())
                .commentOwnerId(accountRepository.getAccountById(dto.getCommentOwnerId().getId()).get())
                .text(dto.getText())
                .build();
        return CoursePartCommentDto.from(coursePartCommentRepository.save(save));
    }
}
