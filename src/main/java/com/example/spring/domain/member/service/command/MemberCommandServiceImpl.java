package com.example.spring.domain.member.service.command;

import com.example.spring.domain.member.converter.MemberConverter;
import com.example.spring.domain.member.dto.MemberReqDTO;
import com.example.spring.domain.member.dto.MemberResDTO;
import com.example.spring.domain.member.entity.Member;
import com.example.spring.domain.member.entity.mapping.MemberFood;
import com.example.spring.domain.member.entity.mapping.MemberMission;
import com.example.spring.domain.member.exception.FoodException;
import com.example.spring.domain.member.exception.code.FoodErrorCode;
import com.example.spring.domain.member.repository.FoodRepository;
import com.example.spring.domain.member.repository.MemberFoodRepository;
import com.example.spring.domain.member.repository.MemberMissionRepository;
import com.example.spring.domain.member.repository.MemberRepository;
import com.example.spring.domain.mission.repository.MissionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ){
        // 사용자 생성
        Member member = MemberConverter.toMember(dto);
        // DB 적용
        memberRepository.save(member);

        // 선호 음식 존재 여부 확인
        if (dto.preferCategory().size() > 1){
            List<MemberFood> memberFood = dto.preferCategory().stream()
                    .map(id -> MemberFood.builder()
                            .member(member)
                            .food(foodRepository.findById(id)
                                    .orElseThrow(() -> new FoodException(FoodErrorCode.NOT_FOUND)))
                            .build()
                    )
                    .collect(Collectors.toList());

            memberFoodRepository.saveAll(memberFood);
        }


        // 응답 DTO 생성
        return MemberConverter.toJoinDTO(member);
    }

    // 미션 도전하기
    @Override
    @Transactional
    public MemberResDTO.newMissionDTO newMission(
            MemberReqDTO.newMissionDTO dto
    ) {
        var memberRef = memberRepository.getReferenceById(dto.memberId());
        var missionRef = missionRepository.getReferenceById(dto.missionId());
        MemberMission memberMission = MemberConverter.toMemberMission(memberRef, missionRef);

        memberMissionRepository.save(memberMission);

        return MemberConverter.toMemberMissionDTO(memberMission);
    }
}