package com.example.demo.domain.store.entity;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.domain.review.entity.Review;
import com.example.demo.global.common.BaseEntity;
import com.example.demo.global.enums.FoodCategory;
import com.example.demo.global.enums.Region;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "store")
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "region_id", length = 20)
    private Region regionId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 20)
    @Builder.Default
    private FoodCategory category = FoodCategory.ETC;

    // 연관관계
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();
}