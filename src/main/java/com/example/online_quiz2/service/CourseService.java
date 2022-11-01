package com.example.online_quiz2.service;

import com.example.online_quiz2.base.service.BaseEntityService;
import com.example.online_quiz2.domain.Course;
import com.example.online_quiz2.domain.User;

import java.text.ParseException;
import java.util.Optional;


public interface CourseService extends BaseEntityService<Course, Long> {
 Course save(Course course, String dateEndCourse, String dateStartCourse) throws ParseException;

 Course saveProfessor(Optional<User> user, Optional<Course> course);

 String spiltCourseId(String id);

 void saveStudent(Optional<Course> course, String student);

 void deleteStudent(Optional<Course> course, String student);

}
