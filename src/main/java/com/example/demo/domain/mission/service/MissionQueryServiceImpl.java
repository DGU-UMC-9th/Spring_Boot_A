package com.example.demo.domain.mission.service;

import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.member.repository.MemberMissionRepository;
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

    @Override
    public Page<MemberMission> getChallengingMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findChallengingMissions(memberId, pageable);
    }

    @Override
    public Page<MemberMission> getCompletedMissions(Long memberId, Pageable pageable) {
        return memberMissionRepository.findCompletedMissions(memberId, pageable);
    }
}