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
import com.umc.training.domain.mission.dto.response.MemberMissionResponseDTO;
import com.umc.training.domain.mission.dto.response.MissionResponseDTO;
import com.umc.training.domain.mission.entity.repository.MissionRepository;
import com.umc.training.domain.store.entity.Store;
import com.umc.training.domain.store.exception.StoreException;
import com.umc.training.domain.store.exception.code.StoreErrorCode;
import com.umc.training.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

	private final MemberMissionRepository memberMissionRepository;
	private final MemberRepository memberRepository;
	private final MissionRepository missionRepository;
	private final StoreRepository storeRepository;

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

	@Transactional(readOnly = true)
	public List<MissionResponseDTO> getStoreMissions(Long storeId, int page, int size) {

		if (!storeRepository.existsById(storeId)) {
			throw new StoreException(StoreErrorCode.STORE_NOT_FOUND);
		}

		List<Mission> missions = missionRepository.findByStoreId(storeId, PageRequest.of(page, size));

		return missions.stream()
				.map(MissionResponseDTO::new)
				.toList();
	}

	@Transactional(readOnly = true)
	public List<MemberMissionResponseDTO> getMyInProgressMissions(Long userId, int page, int size) {
		if (!memberRepository.existsById(userId)) {
			throw new MemberException(MemberBaseCode.MEMBER_NOT_FOUND);
		}

		List<MemberMission> memberMissions = memberMissionRepository.findByMemberIdAndStatus(
				userId, MissionStatus.IN_PROGRESS, PageRequest.of(page, size));

		return memberMissions.stream()
				.map(MemberMissionResponseDTO::new)
				.toList();
	}
}
