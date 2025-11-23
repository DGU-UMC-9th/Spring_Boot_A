package com.example.spring.domain.member.entity;

import com.example.spring.domain.member.enums.*;

import com.example.spring.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity // 이 클래스가 JPA의 엔티티
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member") // DB 테이블 정의
public class Member extends BaseEntity {

    @Id // DB의 PK 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 전략 선택
    private Long id;

    // @Column: DB의 속성 의미
    // NOT NULL 설정, 글자수 제한 등 세부 설정
    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING) // Enum을 사용할 때 데이터 형태 명시
    @Builder.Default // 초기값 설정(테이블 위에서 작성해야 함)
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "social_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "social_id", nullable = false)
    private Long socialId;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "email", length = 20, nullable = false)
    private String email;

    @Column(name = "phone_number",  length = 15)
    private String phoneNumber;

    @Column(name = "point")
    @Builder.Default
    private Integer point = 0;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    @Column(name = "inactive_date")
    private LocalDate inactiveDate;
}
