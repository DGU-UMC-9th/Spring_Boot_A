package com.example.spring_boot_a.service;

import com.example.spring_boot_a.domain.entity.user.User;
import com.example.spring_boot_a.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException("User not found. id=" + userId));
    }


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
