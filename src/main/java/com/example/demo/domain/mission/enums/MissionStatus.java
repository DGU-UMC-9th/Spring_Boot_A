package com.example.demo.domain.mission.enums;

public enum MissionStatus {
    CHALLENGING("도전중"),
    COMPLETE("완료");

    private final String description;

    MissionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}