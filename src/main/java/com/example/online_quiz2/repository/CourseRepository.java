package com.example.online_quiz2.repository;

import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.domain.Course;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends BaseEntityRepository<Course, Long> {


}
