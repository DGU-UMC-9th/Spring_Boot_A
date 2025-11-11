package com.example.spring_boot_a.api.user.converter;

import com.example.spring_boot_a.api.user.dto.UserRequestDto;
import com.example.spring_boot_a.api.user.dto.UserResponseDto;
import com.example.spring_boot_a.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class UserConverter {

    public User toUser(UserRequestDto.Create request) {
        User user = new User();
        user.setName(request.getName());
        user.setGender(request.getGender());
        user.setBirth(request.getBirth());
        user.setAddress(request.getAddress());
        user.setEmail(request.getEmail());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setSocialType(request.getSocialType());
        user.setPoint(0);
        user.setUpdatedAt(Instant.now());
        return user;
    }

    public UserResponseDto.CreateResult toCreateResult(User user) {
        return UserResponseDto.CreateResult.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public UserResponseDto.Detail toDetail(User user) {
        return UserResponseDto.Detail.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .gender(user.getGender())
                .birth(user.getBirth())
                .address(user.getAddress())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .point(user.getPoint())
                .socialType(user.getSocialType())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public UserResponseDto.Summary toSummary(User user) {
        return UserResponseDto.Summary.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .address(user.getAddress())
                .point(user.getPoint())
                .build();
    }

    public List<UserResponseDto.Summary> toSummaryList(List<User> users) {
        return users.stream()
                .map(this::toSummary)
                .toList();
    }
}