package com.example.demo.domain.review.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 회원이 작성한 리뷰 조회
    Page<Review> findByMemberOrderByCreatedAtDesc(Member member, Pageable pageable);

    // 가게의 리뷰 조회
    Page<Review> findByStoreOrderByCreatedAtDesc(Store store, Pageable pageable);
}