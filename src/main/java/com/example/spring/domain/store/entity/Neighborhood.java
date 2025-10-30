package com.example.spring.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // 이 클래스가 JPA의 엔티티
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 자동 생성
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "Neighborhood") // DB 테이블 정의
public class Neighborhood {
    @Id // DB의 PK 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 생성 전략 선택
    private Long id;

    // @Column: DB의 속성 의미
    // NOT NULL 설정, 글자수 제한 등 세부 설정
    @Column(name = "name", length = 10, nullable = false)
    private String name;

    @Column(name = "city", length = 10, nullable = false)
    private String city;
}
