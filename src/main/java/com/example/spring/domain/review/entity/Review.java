package com.example.spring.domain.review.entity;

import com.example.spring.domain.member.entity.Member;
import com.example.spring.domain.store.entity.Store;
import com.example.spring.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity // 이 클래스가 JPA의 엔티티
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "review") // DB 테이블 정의
public class Review extends BaseEntity {
    @Id // DB의 PK 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 전략 선택
    private Long id;

    // 1:N 관계에서 이 엔티티가 1임을 정의
    // 지연 로딩 선택
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") // FK의 주인 설정
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    // @Column: DB의 속성 의미
    // NOT NULL 설정, 글자수 제한 등 세부 설정
    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "comment", length = 100, nullable = false)
    private String comment;
}
