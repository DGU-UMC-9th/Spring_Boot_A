package com.example.demo.domain.member.enums;

public enum MemberStatus {
    ACTIVE("활성"),
    INACTIVE("휴면"),
    BANNED("정지"),
    WITHDRAWN("탈퇴");

    private final String description;

    MemberStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}