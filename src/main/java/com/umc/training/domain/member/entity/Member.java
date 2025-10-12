package com.umc.training.domain.member.entity;

import com.umc.training.domain.member.entity.enums.Gender;
import com.umc.training.domain.member.entity.enums.MemberStatus;
import com.umc.training.domain.member.entity.enums.Role;
import com.umc.training.domain.member.entity.enums.SocialType;
import com.umc.training.domain.review.entity.Review;
import com.umc.training.domain.terms.entity.MemberAgree;
import com.umc.training.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "address", nullable = false, length = 40)
    private String address;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "specAddress", nullable = false, length = 40)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 10)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10, nullable = true)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 10, nullable = false)
    @ColumnDefault("'GENERAL'")
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_status", length = 10, nullable = false)
    @ColumnDefault("'ACTIVE'")
    private MemberStatus status;

    private LocalDate inactiveDate;

    @Column(name = "point", nullable = false)
    @ColumnDefault("0")
    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberAgree> memberAgreeList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    public void encodePassword(String password){
        this.password = password;
    }
}