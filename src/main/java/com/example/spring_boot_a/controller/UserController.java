package com.example.spring_boot_a.controller;

import com.example.spring_boot_a.domain.entity.user.converter.UserConverter;
import com.example.spring_boot_a.domain.entity.user.dto.UserRequestDto;
import com.example.spring_boot_a.domain.entity.user.dto.UserResponseDto;
import com.example.spring_boot_a.domain.entity.user.User;
import com.example.spring_boot_a.global.apiPayload.code.ApiResponse;
import com.example.spring_boot_a.global.apiPayload.code.GeneralSuccessCode;
import com.example.spring_boot_a.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;


    @PostMapping
    public ResponseEntity<ApiResponse<UserResponseDto.CreateResult>> createUser(
            @Valid @RequestBody UserRequestDto.Create request
    ) {
        User user = userConverter.toUser(request);

        User saved = userService.createUser(user);

        UserResponseDto.CreateResult responseDTO = userConverter.toCreateResult(saved);

        return ResponseEntity
                .status(GeneralSuccessCode.CREATED.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.CREATED, responseDTO));
    }


    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDto.Detail>> getUser(
            @PathVariable Long userId
    ) {
        User user = userService.getUser(userId);

        UserResponseDto.Detail dto = userConverter.toDetail(user);

        return ResponseEntity
                .status(GeneralSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, dto));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponseDto.Summary>>> getUsers() {
        List<User> users = userService.getUsers();

        List<UserResponseDto.Summary> dtos = userConverter.toSummaryList(users);

        return ResponseEntity
                .status(GeneralSuccessCode.OK.getStatus())
                .body(ApiResponse.onSuccess(GeneralSuccessCode.OK, dtos));
    }
}
