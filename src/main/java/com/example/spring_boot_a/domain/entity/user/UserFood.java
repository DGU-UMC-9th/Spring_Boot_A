package com.example.spring_boot_a.domain.entity.user;

import com.example.spring_boot_a.domain.entity.etc.Food;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_user_food", columnNames = {"user_id","food_id"}))
public class UserFood {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userFoodId;

    @ManyToOne(optional = false) @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false) @JoinColumn(name = "food_id")
    private Food food;
}
