package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.member.repository.MemberMissionRepository;
import com.example.demo.domain.member.repository.MemberRepository;
import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.mission.repository.MissionRepository;
import com.example.demo.global.apiPayload.code.status.ErrorStatus;
import com.example.demo.global.apiPayload.exception.GeneralException;
import com.example.demo.global.enums.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    public Page<MemberMission> getChallengingMissions(Long memberId, Pageable pageable) {
        // 회원 존재 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        return memberMissionRepository.findChallengingMissions(memberId, pageable);
    }

    @Override
    public Page<MemberMission> getCompletedMissions(Long memberId, Pageable pageable) {
        // 회원 존재 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        return memberMissionRepository.findCompletedMissions(memberId, pageable);
    }
}