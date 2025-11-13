package com.example.demo.domain.member.entity;

import com.example.demo.domain.mission.entity.Mission;
import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user_mission")
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_mission_id")
    private Long id;

    @Column(name = "is_complete", nullable = false)
    @Builder.Default
    private Boolean isComplete = false;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    // 비즈니스 메서드
    public void completeMission() {
        this.isComplete = true;
    }
}