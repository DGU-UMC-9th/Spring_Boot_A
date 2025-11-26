package com.example.spring_boot_a.service;

import com.example.spring_boot_a.domain.entity.Store;
import com.example.spring_boot_a.domain.entity.mission.Mission;
import com.example.spring_boot_a.domain.entity.user.User;
import com.example.spring_boot_a.domain.entity.user.UserMission;

import com.example.spring_boot_a.repository.MissionRepository;
import com.example.spring_boot_a.repository.StoreRepository;
import com.example.spring_boot_a.repository.UserMissionRepository;
import com.example.spring_boot_a.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
}
