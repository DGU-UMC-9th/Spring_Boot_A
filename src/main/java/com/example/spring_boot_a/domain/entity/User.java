package com.example.spring_boot_a.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user",
        indexes = {
                @Index(name = "ix_user_email", columnList = "email"),
                @Index(name = "ix_user_phone", columnList = "phoneNumber")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_user_email", columnNames = "email"),
                @UniqueConstraint(name = "uq_user_phone", columnNames = "phoneNumber")
        })
@Getter @Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private AddressGu address;

    @Column(length = 255)
    private String detailAddress;

    @Column(length = 100)
    private String socialUid;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private SocialType socialType;

    @Column(nullable = false)
    private Integer point = 0;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 20)
    private String phoneNumber;

    private Instant deletedAt;

    @Column(nullable = false)
    private Instant updatedAt = Instant.now();

    // relations
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserFood> userFoods = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTerm> userTerms = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserMission> userMissions = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Review> reviews = new HashSet<>();
}

