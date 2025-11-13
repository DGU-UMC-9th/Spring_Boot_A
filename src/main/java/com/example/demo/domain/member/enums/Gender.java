package com.example.demo.domain.member.enums;

public enum Gender {
    MALE("남성"),
    FEMALE("여성"),
    NONE("선택 안함");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}