package com.example.demo.domain.mission.entity;

import com.example.demo.domain.member.entity.MemberMission;
import com.example.demo.domain.store.entity.Store;
import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "mission")
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long id;

    @Column(name = "content", length = 200)
    private String content;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "deadline")
    private LocalDate deadline;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    @Builder.Default
    private List<MemberMission> memberMissionList = new ArrayList<>();
}