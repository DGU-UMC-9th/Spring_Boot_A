package com.example.demo.domain.member.enums;

public enum TermType {
    SERVICE("서비스 이용약관", true),
    PRIVACY("개인정보 처리방침", true),
    MARKETING("마케팅 수신 동의", false),
    LOCATION("위치기반 서비스 이용약관", false),
    AGE("만 14세 이상 확인", true);

    private final String description;
    private final boolean required;

    TermType(String description, boolean required) {
        this.description = description;
        this.required = required;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequired() {
        return required;
    }
}