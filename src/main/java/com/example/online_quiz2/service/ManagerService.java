package com.example.online_quiz2.service;

import com.example.online_quiz2.base.service.BaseEntityService;


public interface ManagerService extends BaseEntityService<Manager, Long> {
    Manager loginManager(String password, String username);
}
