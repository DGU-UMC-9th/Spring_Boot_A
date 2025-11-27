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
import com.example.demo.global.enums.Region;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.domain.store.repository.StoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class MissionQueryServiceImpl implements MissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Override
    public MissionResponseDTO.MissionPreViewListDTO getStoreMissions(Long storeId, Integer page) {
        // 1. Store 조회 및 검증
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        // 2. 페이징 설정 (페이지는 0부터 시작하므로 -1)
        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        // 3. 미션 조회
        Page<Mission> missionPage = missionRepository.findAllByStore(store, pageRequest);

        // 4. DTO 변환
        return MissionConverter.toMissionPreViewListDTO(missionPage);
    }

    @Override
    public Page<MemberMission> getChallengingMissions(Long memberId, Pageable pageable) {
        // 회원 존재 확인
        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        return memberMissionRepository.findChallengingMissions(memberId, pageable);
    }
}