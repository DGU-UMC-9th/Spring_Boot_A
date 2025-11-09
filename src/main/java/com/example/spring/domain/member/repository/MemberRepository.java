package com.example.spring.domain.member.repository;

import com.example.spring.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {
    // 마이 페이지 화면 쿼리
    // SELECT id, name, email, phone_number, point FROM member
    List<Member> findAll();
}