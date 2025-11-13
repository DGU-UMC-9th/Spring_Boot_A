package com.example.demo.global.enums;

public enum FoodCategory {
    KOREAN("한식"),
    WESTERN("양식"),
    CHINESE("중식"),
    JAPANESE("일식");

    private final String description;

    FoodCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}