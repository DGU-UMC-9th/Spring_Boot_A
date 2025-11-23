package com.example.spring.domain.member.repository;

import com.example.spring.domain.member.entity.mapping.MemberFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFoodRepository extends JpaRepository<MemberFood,Long> {
}
