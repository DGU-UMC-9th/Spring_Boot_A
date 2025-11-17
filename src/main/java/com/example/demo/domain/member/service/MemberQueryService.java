package com.example.demo.domain.member.service;

import com.example.demo.domain.member.entity.Member;

public interface MemberQueryService {
    Member getMyPageInfo(Long memberId);
}