package com.example.online_quiz2.service;

import com.example.online_quiz2.base.service.BaseEntityService;
import com.example.online_quiz2.domain.User;

import java.util.List;
import java.util.Optional;


public interface UserService extends BaseEntityService<User, Long> {
    User save(User user);

    User updateUser(Optional<User> user);

    Optional<User> findCustom(Long id);

    List<User> AllByAdvanceSearch(User user);

    List<User> findAllProfessor();

    String splitId(String id);

    List<User> findAllByStudent();

    User checkUserLogin(String username, String password);
}
