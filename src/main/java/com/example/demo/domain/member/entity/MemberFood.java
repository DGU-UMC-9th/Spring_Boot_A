package com.example.demo.domain.member.entity;

import com.example.demo.global.common.BaseEntity;
import com.example.demo.global.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user_food")
public class MemberFood extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_food_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "food_category", nullable = false, length = 20)
    private FoodCategory foodCategory;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;
}