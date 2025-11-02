package com.umc.training.domain.mission.entity;

import com.umc.training.domain.member.entity.MemberMission;
import com.umc.training.domain.store.entity.Store;
import com.umc.training.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "missions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name="rewards", nullable = false)
    private int reward;

    @Column(name = "deadline", nullable = false)
    private LocalDate deadline;

    @Column(name = "mission_spec", nullable = false, length = 100)
    private String missionSpec;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList;
}
