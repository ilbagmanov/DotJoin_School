package ru.itis.dotjoinrestapi.services;

import ru.itis.dotjoinrestapi.dto.CoursePartCommentDto;
import ru.itis.dotjoinrestapi.models.CoursePartComment;

import java.util.List;

public interface CoursePartCommentService {
    List<CoursePartCommentDto> getPageComment(Long id);

    CoursePartCommentDto save(CoursePartCommentDto dto);
}
