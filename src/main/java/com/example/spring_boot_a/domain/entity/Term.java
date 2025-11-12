package com.example.spring_boot_a.domain.entity;

import com.example.spring_boot_a.domain.entity.enums.TermType;
import com.example.spring_boot_a.domain.entity.user.UserTerm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter
public class Term {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long termId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 12)
    private TermType name;

    @OneToMany(mappedBy = "term", cascade = CascadeType.REMOVE)
    private Set<UserTerm> userTerms = new HashSet<>();
}
