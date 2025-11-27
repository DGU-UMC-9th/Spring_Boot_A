package com.example.spring_boot_a.repository;

import com.example.spring_boot_a.domain.entity.review.Review;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByUser_UserId(Long userId, Pageable pageable);


    Page<Review> findByStore_StoreIdOrderByCreatedAtDesc(Long storeId, Pageable pageable);


    Page<Review> findByStore_StoreIdAndStarGreaterThanEqualAndStarLessThanOrderByCreatedAtDesc(
            Long storeId, Float min, Float max, Pageable pageable);

    Page<Review> findByStore_StoreIdAndStarEqualsOrderByCreatedAtDesc(
            Long storeId, Float star, Pageable pageable);


    Page<Review> findByUser_UserIdOrderByCreatedAtDesc(Long userId, Pageable pageable);


    @Query("select coalesce(avg(r.star),0) from Review r where r.store.storeId = :storeId")
    double findAvgStarByStore(@Param("storeId") Long storeId);


    @Query("""
           select 
             sum(case when r.star = 5 then 1 else 0 end),
             sum(case when r.star >= 4 and r.star < 5 then 1 else 0 end),
             sum(case when r.star >= 3 and r.star < 4 then 1 else 0 end),
             sum(case when r.star >= 2 and r.star < 3 then 1 else 0 end),
             sum(case when r.star >= 1 and r.star < 2 then 1 else 0 end)
           from Review r
           where r.store.storeId = :storeId
           """)
    Object[] countBucketsByStore(@Param("storeId") Long storeId);

}
