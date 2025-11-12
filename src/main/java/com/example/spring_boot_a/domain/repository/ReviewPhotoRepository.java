package com.example.spring_boot_a.domain.repository;

import com.example.spring_boot_a.domain.entity.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Long> {
    List<ReviewPhoto> findByReview_ReviewIdIn(Collection<Long> reviewIds);
}
