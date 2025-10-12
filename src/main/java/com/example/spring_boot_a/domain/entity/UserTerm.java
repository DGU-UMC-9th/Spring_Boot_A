package com.example.spring_boot_a.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_user_term", columnNames = {"user_id","term_id"}))
public class UserTerm {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTermId;

    @ManyToOne(optional = false) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false) @JoinColumn(name = "term_id")
    private Term term;
}
