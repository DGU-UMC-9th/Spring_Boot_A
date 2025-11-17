package com.example.demo.domain.member.repository;

import com.example.demo.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 회원 조회
    Optional<Member> findByEmail(String email);

    // 이메일 존재 여부 확인
    boolean existsByEmail(String email);

    // 마이페이지용 회원 정보 조회 (fetch join으로 N+1 방지)
    @Query("SELECT m FROM Member m " +
            "LEFT JOIN FETCH m.reviewList " +
            "WHERE m.id = :memberId")
    Optional<Member> findMemberWithReviews(@Param("memberId") Long memberId);
}