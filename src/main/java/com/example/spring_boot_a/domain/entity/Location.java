package com.example.spring_boot_a.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_location_name", columnNames = "name"))
public class Location {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    @Column(nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "location")
    private Set<Store> stores = new HashSet<>();
}
