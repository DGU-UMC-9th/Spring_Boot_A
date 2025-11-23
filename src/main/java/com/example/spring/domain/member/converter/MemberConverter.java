package com.example.spring.domain.member.converter;

import com.example.spring.domain.member.dto.MemberReqDTO;
import com.example.spring.domain.member.dto.MemberResDTO;
import com.example.spring.domain.member.entity.Member;
import com.example.spring.domain.member.entity.mapping.MemberMission;
import com.example.spring.domain.mission.entity.Mission;

public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ){
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ){
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .build();
    }

    // newMission Entity -> DTO
    public static MemberResDTO.newMissionDTO toMemberMissionDTO(
            MemberMission memberMission
    ){
        return MemberResDTO.newMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .build();
    }

    // DTO -> MemberMission Entity
    public static MemberMission toMemberMission(
        Member member, Mission mission
    ){
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }
}