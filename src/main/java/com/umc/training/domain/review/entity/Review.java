package com.umc.training.domain.review.entity;

import com.umc.training.domain.member.entity.Member;
import com.umc.training.domain.store.entity.Store;
import com.umc.training.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @Column(name = "score", nullable = false)
    private Float score;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<Reply> replyList;

}
