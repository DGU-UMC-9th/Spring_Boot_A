package com.example.demo.domain.review.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 회원이 작성한 리뷰 조회
    Page<Review> findByMember(Member member, Pageable pageable);

    // 가게의 리뷰 조회
    Page<Review> findByStore(Store store, Pageable pageable);

    // 회원의 최근 리뷰 조회 (마이페이지용)
    @Query("SELECT r FROM Review r " +
            "WHERE r.member = :member " +
            "ORDER BY r.createdAt DESC")
    List<Review> findRecentReviewsByMember(
            @Param("member") Member member,
            Pageable pageable
    );
}