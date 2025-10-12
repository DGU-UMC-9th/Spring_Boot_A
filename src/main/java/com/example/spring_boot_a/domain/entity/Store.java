package com.example.spring_boot_a.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter
public class Store {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Long managerNumber;

    @Column(nullable = false, length = 255)
    private String detailAddress;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private Set<Mission> missions = new HashSet<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private Set<Review> reviews = new HashSet<>();
}
