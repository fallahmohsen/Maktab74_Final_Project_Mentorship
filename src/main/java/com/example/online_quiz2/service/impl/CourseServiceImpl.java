package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.service.impl.BaseEntityServiceImpl;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.repository.CourseRepository;
import com.example.online_quiz2.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CourseServiceImpl extends BaseEntityServiceImpl<Course, Long, CourseRepository> implements CourseService {
    public CourseServiceImpl(CourseRepository repository) {
        super(repository);
    }

    @Transactional
    @Override
    public Course save(Course course, String dateEndCourse, String dateStartCourse) throws ParseException {
        Date dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(dateEndCourse);
        Date dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(dateStartCourse);
        course.setCourseStartDate(dateStart);
        course.setCourseEndDate(dateEnd);
        return repository.save(course);
    }

    @Transactional
    @Override
    public Course saveProfessor(Optional<User> user, Optional<Course> course) {
        User user1 = user.get();
        ;
        Course course1 = course.get();
        course1.setProfessor(user1.getName());

        return repository.save(course1);
    }

    @Override
    public String spiltCourseId(String id) {
        String[] courseId = id.split(":", 2);
        return courseId[0];
    }

    @Transactional
    @Override
    public void saveStudent(Optional<Course> course, String student) {
        Course course1 = course.get();
        student = student.replaceAll(",", "");
        student = student.trim();
        if (!course1.getUniversityStudent().contains(student)) {
            student += "-";
            student += course1.getUniversityStudent();
            course1.setUniversityStudent(student);
            repository.save(course1);
        }


    }

    @Transactional
    @Override
    public void deleteStudent(Optional<Course> course, String student) {
        Course course1 = course.get();
        student = student.replaceAll(",", "");
        student = student.trim();
        String studentList = null;
        if (student != null) {
            studentList = course1.getUniversityStudent();
            studentList = studentList.replaceAll(student, "");
            studentList = studentList.trim();
        }
        course1.setUniversityStudent(studentList);
        repository.save(course1);
    }


}
