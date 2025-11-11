package com.example.spring_boot_a.domain.entity.etc;

import com.example.spring_boot_a.domain.entity.user.UserFood;
import com.example.spring_boot_a.domain.entity.enums.FoodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity @Getter @Setter
public class Food {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long foodId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private FoodType name;

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    private Set<UserFood> userFoods = new HashSet<>();
}

