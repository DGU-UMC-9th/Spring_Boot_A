package com.example.spring_boot_a.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Reply {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Lob @Column(nullable = false)
    private String content;

    @ManyToOne(optional = false) @JoinColumn(name = "review_id")
    private Review review;
}
