package com.umc.training.domain.member.entity.repository;

import com.umc.training.domain.member.entity.MemberMission;
import org.springframework.data.repository.Repository;

public interface MemberMissionRepository extends Repository<MemberMission, Long> {
    
    MemberMission save(MemberMission memberMission);
}

