package com.example.online_quiz2.repository;

import com.example.online_quiz2.base.repository.BaseEntityRepository;
import com.example.online_quiz2.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseEntityRepository<User, Long> {
    Optional<User> findById(Long id);

    @Query("select u from User u where u.role = :role")
    List<User> findByProfessor(@Param("role") String professor);

    @Query("select u from User u where u.role =:role")
    List<User> findByStudent(@Param("role") String Student);

    User findByUsername(@Param("username") String username);
}
