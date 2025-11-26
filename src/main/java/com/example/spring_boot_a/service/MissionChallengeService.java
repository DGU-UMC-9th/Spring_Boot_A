package com.example.spring_boot_a.service;

import com.example.spring_boot_a.domain.entity.Store;
import com.example.spring_boot_a.domain.entity.mission.Mission;
import com.example.spring_boot_a.domain.entity.mission.dto.StoreMissionListResponse;
import com.example.spring_boot_a.domain.entity.mission.dto.converter.StoreMissionConverter;
import com.example.spring_boot_a.domain.entity.user.User;
import com.example.spring_boot_a.domain.entity.user.UserMission;

import com.example.spring_boot_a.repository.MissionRepository;
import com.example.spring_boot_a.repository.StoreRepository;
import com.example.spring_boot_a.repository.UserMissionRepository;
import com.example.spring_boot_a.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class MissionChallengeService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;

    private static final int PAGE_SIZE = 10;

    @Transactional
    public UserMission challengeMission(Long storeId, Long missionId, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다. userId=" + userId));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다. storeId=" + storeId));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 미션입니다. missionId=" + missionId));

        if (!mission.getStore().getStoreId().equals(store.getStoreId())) {
            throw new IllegalArgumentException("해당 가게의 미션이 아닙니다.");
        }

        if (mission.getDeadline().isBefore(LocalDate.now())) {
            throw new IllegalStateException("이미 마감된 미션입니다.");
        }

        UserMission userMission = UserMission.start(user, mission);
        return userMissionRepository.save(userMission);
    }

    @Transactional(readOnly = true)
    public StoreMissionListResponse getStoreMissions(Long storeId, int pageIndex) {

        // 가게 존재 여부 체크 (없으면 400)
        storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다. storeId=" + storeId));

        var pageable = PageRequest.of(
                pageIndex,
                PAGE_SIZE,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        var missionPage = missionRepository.findByStore_StoreId(storeId, pageable);

        return StoreMissionListResponse.builder()
                .page(pageIndex + 1) // 다시 1-base 로
                .size(PAGE_SIZE)
                .totalElements(missionPage.getTotalElements())
                .totalPages(missionPage.getTotalPages())
                .missions(StoreMissionConverter.toSummaryList(missionPage.getContent()))
                .build();
    }
}
