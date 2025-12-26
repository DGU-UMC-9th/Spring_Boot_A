package com.example.demo.domain.review.repository;

import com.example.demo.domain.member.entity.Member;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.domain.store.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

    Page<Review> findByMemberOrderByCreatedAtDesc(Member member, Pageable pageable);
    Page<Review> findByStoreOrderByCreatedAtDesc(Store store, Pageable pageable);
    Page<Review> findAllByMember(Member member, PageRequest pageRequest);
}