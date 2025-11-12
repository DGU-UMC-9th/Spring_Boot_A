package com.example.spring_boot_a.domain.entity.etc;

import com.example.spring_boot_a.domain.entity.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class ReviewPhoto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewPhotoId;

    @Column(nullable = false, length = 500)
    private String photoUrl;

    @ManyToOne(optional = false) @JoinColumn(name = "review_id")
    private Review review;
}
