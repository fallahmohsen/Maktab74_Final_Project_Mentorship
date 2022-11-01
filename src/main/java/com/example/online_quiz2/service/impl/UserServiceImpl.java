package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.service.impl.BaseEntityServiceImpl;
import com.example.online_quiz2.domain.User;
import com.example.online_quiz2.repository.UserRepository;
import com.example.online_quiz2.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends BaseEntityServiceImpl<User, Long, UserRepository> implements UserService {
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User save(User user) {
        user.setStatus("Awaiting confirmation");

        return repository.save(user);
    }

    @Override
    @Transactional
    public User updateUser(Optional<User> user) {
        User user1 = user.get();
        user1.setStatus("accept");
        return repository.save(user1);
    }

    @Override
    public Optional<User> findCustom(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<User> AllByAdvanceSearch(User user) {
        return repository.findAll(
                (root, query, criteriaBuilder) -> {
                    List<Predicate> predicates = new ArrayList<>();
                    setNamePredicate(predicates, root, criteriaBuilder, user.getName());
                    setGenderPredicate(predicates, root, criteriaBuilder, user.getGender());
                    setPasswordPredicate(predicates, root, criteriaBuilder, user.getPassword());
                    setUsernamePredicate(predicates, root, criteriaBuilder, user.getUsername());
                    setRolePredicate(predicates, root, criteriaBuilder, user.getRole());
                    setStatusPredicate(predicates, root, criteriaBuilder, user.getStatus());

                    return criteriaBuilder.and(
                            predicates.toArray(new Predicate[0])

                    );
                }
        );

    }

    @Transactional
    @Override
    public List<User> findAllProfessor() {
        List<User> userList = repository.findByProfessor("professor");
        return userList;
    }

    @Override
    public String splitId(String id) {
        String[] userID = id.split(":", 2);
        return userID[1];
    }

    @Override
    public List<User> findAllByStudent() {
        List<User> user = repository.findByStudent("student");
        return user;
    }

    @Override
    public User checkUserLogin(String username, String password) {
        User user = repository.findByUsernameAndAndPassword(username, password);
        if (user != null) {
            if (user.getRole() == "Professor") {
                return user;
            } else {
                throw new RuntimeException();
            }

        }
        return null;
    }

    private void setNamePredicate(List<Predicate> predicates, Root<User> root, CriteriaBuilder criteriaBuilder, String name) {
        if (name != null && !name.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("name"),
                            "%" + name + "%"
                    )
            );
        }
    }

    private void setStatusPredicate(List<Predicate> predicates, Root<User> root, CriteriaBuilder criteriaBuilder, String status) {
        if (status != null && !status.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("status"),
                            "%" + status + "%"
                    )
            );
        }
    }

    private void setRolePredicate(List<Predicate> predicates, Root<User> root, CriteriaBuilder criteriaBuilder, String role) {
        if (role != null && !role.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("role"),
                            "%" + role + "%"
                    )
            );
        }
    }

    private void setUsernamePredicate(List<Predicate> predicates, Root<User> root, CriteriaBuilder criteriaBuilder, String username) {
        if (username != null && !username.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("username"),
                            "%" + username + "%"
                    )
            );
        }
    }

    private void setPasswordPredicate(List<Predicate> predicates, Root<User> root, CriteriaBuilder criteriaBuilder, String password) {
        if (password != null && !password.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("password"),
                            "%" + password + "%"
                    )
            );
        }
    }

    private void setGenderPredicate(List<Predicate> predicates, Root<User> root, CriteriaBuilder criteriaBuilder, String gender) {
        if (gender != null && !gender.isEmpty()) {
            predicates.add(
                    criteriaBuilder.like(
                            root.get("gender"),
                            "%" + gender + "%"
                    )
            );
        }
    }


}
