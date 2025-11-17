package com.example.demo.global.home.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.repository.MemberMissionRepository;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.global.apiPayload.code.status.ErrorStatus;
import com.example.demo.global.apiPayload.exception.GeneralException;
import com.example.demo.global.enums.Region;
import com.example.demo.global.home.dto.HomeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeQueryServiceImpl implements HomeQueryService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    @Override
    public HomeResponseDTO.HomeDTO getHomeInfo(Long memberId, Region region, Pageable pageable) {
        // 회원 정보 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 현재 지역에서 진행중인 미션 개수
        Long currentMissions = memberMissionRepository.countChallengingMissionsByRegion(
                memberId,
                region
        );

        // 도전 가능한 미션 목록
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegion(
                region,
                memberId,
                LocalDate.now(),
                pageable
        );

        // DTO 변환
        List<HomeResponseDTO.AvailableMissionDTO> missionList = missions.stream()
                .map(mission -> {
                    long daysUntil = ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadline());
                    String dDay = "D-" + daysUntil;
                    String pointText = mission.getPoint() + " P 적립";

                    return HomeResponseDTO.AvailableMissionDTO.builder()
                            .storeName(mission.getStore().getName())
                            .category(mission.getStore().getCategory().getDescription())
                            .content(mission.getContent())
                            .pointText(pointText)
                            .dDay(dDay)
                            .build();
                })
                .collect(Collectors.toList());

        HomeResponseDTO.PageInfoDTO pageInfo = HomeResponseDTO.PageInfoDTO.builder()
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();

        return HomeResponseDTO.HomeDTO.builder()
                .point(member.getPoint())
                .currentLocation(region)
                .currentMissions(currentMissions)
                .targetMissions(10)
                .bonusPoint(1000)
                .availableMissions(missionList)
                .pageInfo(pageInfo)
                .build();
    }
}