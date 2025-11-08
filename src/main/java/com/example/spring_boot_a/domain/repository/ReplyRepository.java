package com.example.spring_boot_a.domain.repository;

import com.example.spring_boot_a.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByReview_ReviewIdIn(Collection<Long> reviewIds);
}
