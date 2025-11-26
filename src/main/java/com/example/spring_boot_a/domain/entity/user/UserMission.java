package com.example.spring_boot_a.domain.entity.user;

import com.example.spring_boot_a.domain.entity.enums.UserMissionStatus;
import com.example.spring_boot_a.domain.entity.mission.Mission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter @Setter
@NoArgsConstructor
public class UserMission {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userMissionId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserMissionStatus status;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    private Instant completedAt;

    public static UserMission start(User user, Mission mission) {
        UserMission um = new UserMission();
        um.user = user;
        um.mission = mission;
        um.status = UserMissionStatus.IN_PROGRESS;
        return um;
    }

    public void complete() {
        this.status = UserMissionStatus.COMPLETE;
        this.completedAt = Instant.now();
    }
}
