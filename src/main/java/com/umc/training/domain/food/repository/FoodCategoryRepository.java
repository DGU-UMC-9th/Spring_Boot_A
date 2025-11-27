package com.umc.training.domain.food.repository;

import com.umc.training.domain.food.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
    boolean existsById(Long id);
}
