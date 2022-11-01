package com.example.online_quiz2.service.impl;

import com.example.online_quiz2.base.service.impl.BaseEntityServiceImpl;
import com.example.online_quiz2.service.ManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ManagerServiceImpl extends BaseEntityServiceImpl<Manager, Long, ManagerRepository> implements ManagerService {
    public ManagerServiceImpl(ManagerRepository repository) {
        super(repository);
    }

    @Override
    public Manager loginManager(String password, String username) {
        return repository.findByUsernameAndPassword(username, password);

    }
}
