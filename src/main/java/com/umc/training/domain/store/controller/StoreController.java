package com.umc.training.domain.store.controller;

import com.umc.training.domain.store.dto.request.StoreAddRequestDTO;
import com.umc.training.domain.store.service.StoreService;
import com.umc.training.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    // 특정 지역에 가게 추가하기
    @PostMapping("/region/{regionId}")
    public ApiResponse<Void> addStore(
            @PathVariable("regionId") Long regionId,
            @RequestBody StoreAddRequestDTO request) {

        storeService.addStore(regionId, request);
        return ApiResponse.onSuccess(null);
    }
}
