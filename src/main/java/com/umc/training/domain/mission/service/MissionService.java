package com.umc.training.domain.mission.service;

import com.umc.training.domain.member.entity.Member;
import com.umc.training.domain.member.entity.MemberMission;
import com.umc.training.domain.member.entity.exception.code.MemberBaseCode;
import com.umc.training.domain.member.entity.exception.code.MemberException;
import com.umc.training.domain.member.entity.repository.MemberMissionRepository;
import com.umc.training.domain.member.entity.repository.MemberRepository;
import com.umc.training.domain.mission.entity.Mission;
import com.umc.training.domain.mission.entity.enums.MissionStatus;
import com.umc.training.domain.mission.exception.MissionException;
import com.umc.training.domain.mission.exception.code.MissionErrorCode;
import com.umc.training.domain.mission.entity.repository.MissionRepository;
import com.umc.training.domain.store.entity.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

	private final MemberMissionRepository memberMissionRepository;
	private final MemberRepository memberRepository;
	private final MissionRepository missionRepository;

	public void challengeMission(Long missionId, Long userId) {

		Member member = memberRepository.findById(userId)
				.orElseThrow(() -> new MemberException(MemberBaseCode.MEMBER_NOT_FOUND));

		Mission mission = missionRepository.findById(missionId)
				.orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

		Store store = mission.getStore();

		MemberMission memberMission = MemberMission.builder()
				.member(member)
				.mission(mission)
				.store(store)
				.deadline(mission.getDeadline())
				.status(MissionStatus.IN_PROGRESS)
				.missionContent(mission.getMissionSpec())
				.createdAt(LocalDate.now())
				.build();

		memberMissionRepository.save(memberMission);
	}
}
