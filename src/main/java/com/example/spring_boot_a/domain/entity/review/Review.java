package com.example.spring_boot_a.domain.entity.review;

import com.example.spring_boot_a.domain.entity.etc.ReviewPhoto;
import com.example.spring_boot_a.domain.entity.Store;
import com.example.spring_boot_a.domain.entity.etc.Reply;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @Lob @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(nullable = false)
    private Float star;

    @ManyToOne(optional = false) @JoinColumn(name = "store_id")
    private Store store;

    @JoinColumn(name = "user_id")
    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewPhoto> photos = new HashSet<>();

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reply> replies = new HashSet<>();
}
