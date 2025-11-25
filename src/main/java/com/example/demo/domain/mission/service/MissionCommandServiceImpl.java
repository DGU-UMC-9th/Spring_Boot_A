package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.member.repository.MemberMissionRepository;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.converter.MissionConverter;
import com.example.demo.domain.mission.dto.MissionResponseDTO;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.global.apiPayload.code.status.ErrorStatus;
import com.example.demo.global.apiPayload.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MissionResponseDTO.ChallengeResultDTO challengeMission(Long memberId, Long missionId) {
        // 1. 회원 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        // 2. 미션 존재 확인
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        // 3. 중복 도전 확인 (미완료 상태만)
        boolean alreadyChallenging = memberMissionRepository
                .existsByMemberAndMissionAndIsCompleteFalse(member, mission);

        if (alreadyChallenging) {
            throw new GeneralException(ErrorStatus.MISSION_ALREADY_CHALLENGING);
        }

        // 4. MemberMission 엔티티 생성 및 저장
        MemberMission memberMission = MissionConverter.toMemberMission(member, mission);
        memberMissionRepository.save(memberMission);

        // 5. 응답 DTO 생성
        return MissionConverter.toChallengeResultDTO(memberMission);
    }
}