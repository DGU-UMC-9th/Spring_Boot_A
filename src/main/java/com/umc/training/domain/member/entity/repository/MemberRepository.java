package com.umc.training.domain.member.entity.repository;

import com.umc.training.domain.member.entity.Member;
import org.springframework.data.repository.Repository;

public interface MemberRepository extends Repository<Member, Long> {

    // 미션 2
    Member findById(Member member);

}
