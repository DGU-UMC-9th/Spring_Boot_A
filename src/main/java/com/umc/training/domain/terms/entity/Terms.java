package com.umc.training.domain.terms.entity;

import com.umc.training.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Terms extends BaseEntity {

    @Column(name = "title", nullable = false, length = 20)
    private String title;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "optional", nullable = false)
    private boolean Optional;

    @OneToMany(mappedBy = "terms", cascade = CascadeType.ALL)
    private List<MemberAgree> memberAgreeList;
}