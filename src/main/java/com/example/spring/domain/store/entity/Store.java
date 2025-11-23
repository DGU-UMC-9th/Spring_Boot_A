package com.example.spring.domain.store.entity;

import com.example.spring.domain.member.enums.Status;
import com.example.spring.domain.member.enums.FoodName;
import com.example.spring.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity // 이 클래스가 JPA의 엔티티
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store") // DB 테이블 정의
public class Store extends BaseEntity {

    @Id // DB의 PK 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 전략 선택
    private Long id;

    // 1:N 관계에서 이 엔티티가 1임을 정의
    // 지연 로딩 선택
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighborhood_id") // FK의 주인 설정
    private Neighborhood neighborhood;

    // @Column: DB의 속성 의미
    // NOT NULL 설정, 글자수 제한 등 세부 설정
    @Column(name = "name", length = 20, nullable = false)
    private String name;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodName category;

    @Column(name = "opening_hours", length = 30)
    private String openingHours;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.ACTIVE;

    @Column(name = "inactive_date")
    private LocalDate inactiveDate;
}
