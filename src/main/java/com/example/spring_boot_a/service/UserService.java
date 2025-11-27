package com.example.spring_boot_a.service;

import com.example.spring_boot_a.domain.entity.user.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUser(Long userId);

    List<User>  getUsers();
}
