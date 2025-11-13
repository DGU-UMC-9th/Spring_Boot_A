package com.example.demo.domain.member.entity;

import com.example.demo.domain.member.enums.TermType;
import com.example.demo.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "user_term")
public class MemberTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_term_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "term_type", nullable = false, length = 20)
    private TermType termType;

    @Column(name = "is_agreed", nullable = false)
    @Builder.Default
    private Boolean isAgreed = false;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;
}