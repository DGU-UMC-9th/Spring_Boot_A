package com.example.spring.domain.mission.entity;

import com.example.spring.domain.store.entity.Store;
import com.example.spring.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity // 이 클래스가 JPA의 엔티티
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission") // DB 테이블 정의
public class Mission extends BaseEntity {
    @Id // DB의 PK 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 전략 선택
    private Long id;

    // 1:N 관계에서 이 엔티티가 1임을 정의
    // 지연 로딩 선택
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id") // FK의 주인 설정
    private Store store;

    // @Column: DB의 속성 의미
    // NOT NULL 설정, 글자수 제한 등 세부 설정
    @Column(name = "mov")
    private Long mov;

    @Column(name = "point")
    private Long point;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
}
