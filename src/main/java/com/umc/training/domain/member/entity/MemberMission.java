package com.umc.training.domain.member.entity;

import com.umc.training.domain.mission.entity.Mission;
import com.umc.training.domain.mission.entity.enums.MissionStatus;
import com.umc.training.domain.store.entity.Store;
import com.umc.training.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "missionStatus", nullable = false)
    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @Column(name = "mission_content", nullable = false)
    private String missionContent;

    @Column(name = "create_at", nullable = false)
    private LocalDate createdAt;
}