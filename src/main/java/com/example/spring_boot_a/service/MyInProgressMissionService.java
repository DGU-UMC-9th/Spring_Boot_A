package com.example.spring_boot_a.service;

import com.example.spring_boot_a.domain.entity.enums.UserMissionStatus;
import com.example.spring_boot_a.domain.entity.mission.dto.MyInProgressMissionResponse;
import com.example.spring_boot_a.domain.entity.mission.dto.converter.MyInProgressMissionConverter;
import com.example.spring_boot_a.global.apiPayload.code.ApiResponse;
import com.example.spring_boot_a.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyInProgressMissionService {

    private static final int PAGE_SIZE = 10;

    private final UserMissionRepository userMissionRepository;

    public ApiResponse.PageResponse<MyInProgressMissionResponse> getMyInProgressMissions(Long userId, int pageIndex) {

        var pageable = PageRequest.of(
                pageIndex,
                PAGE_SIZE,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        var page = userMissionRepository
                .findByUser_UserIdAndStatus(userId, UserMissionStatus.IN_PROGRESS, pageable);

        return ApiResponse.PageResponse.<MyInProgressMissionResponse>builder()
                .content(MyInProgressMissionConverter.fromList(page.getContent()))
                .page(pageIndex + 1)
                .size(PAGE_SIZE)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}
