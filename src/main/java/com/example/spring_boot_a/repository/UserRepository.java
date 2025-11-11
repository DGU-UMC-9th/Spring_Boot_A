package com.example.spring_boot_a.repository;

import com.example.spring_boot_a.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
