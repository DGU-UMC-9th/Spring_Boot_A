package com.example.spring_boot_a.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_user_mission", columnNames = {"user_id","mission_id"}))
public class UserMission {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userMissionId;

    @Column(nullable = false)
    private Boolean isComplete = false;

    @ManyToOne(optional = false) @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(optional = false) @JoinColumn(name = "user_id")
    private User user;
}
