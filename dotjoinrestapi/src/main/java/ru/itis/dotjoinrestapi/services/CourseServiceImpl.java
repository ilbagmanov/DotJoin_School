package ru.itis.dotjoinrestapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.dotjoinrestapi.dto.CourseDto;
import ru.itis.dotjoinrestapi.models.Account;
import ru.itis.dotjoinrestapi.models.Course;
import ru.itis.dotjoinrestapi.repositories.AccountRepository;
import ru.itis.dotjoinrestapi.repositories.CourseRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Value("${jwt.header}")
    private String header;

    @Value("${jwt.secretKey}")
    private String secretKey;

    @Override
    public CourseDto createCourse(CourseDto courseDto) {
        Course course = Course.builder()
                .ownerId(accountService.getAccountById(courseDto.getOwnerId()).get())
                .title(courseDto.getTitle())
                .description(courseDto.getDescription())
                .price(courseDto.getPrice())
                .startDate((new Timestamp(System.currentTimeMillis())))
                .build();
        return CourseDto.from(courseRepository.save(course));
    }

    @Override
    public Account addCourse(Long courseId, Account account) {
        Course course = courseRepository.getCourseById(courseId);
        if (course != null && !course.getAccounts().contains(account)) {
            account.getCourses().add(course);
            course.getAccounts().add(account);
            courseRepository.save(course);
        }
        return account;
    }

    @Override
    public List<CourseDto> getAllCourses() {
        return CourseDto.from(courseRepository.findAll());
    }


    @Override
    public List<CourseDto> getNotBuyCourses(Long userId) {
        return CourseDto.from(courseRepository.getCoursesNotBuyWithAccount(userId));
    }

    @Override
    public CourseDto getCourseDtoById(Long courseId) {
        return CourseDto.from(courseRepository.getCourseById(courseId));
    }

    @Override
    public CourseDto getCourseDtoByIdAndOwnerId(Long courseId, Long ownerId) {
        return CourseDto.from(courseRepository.getCourseByIdAndOwnerId(courseId, ownerId));
    }

    @Override
    public List<CourseDto> getCreatedCourses(Long userId) {
        return CourseDto.from(courseRepository.getCoursesByOwnerId(accountRepository.getAccountById(userId).get()));
    }
}
