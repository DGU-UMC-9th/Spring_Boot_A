package com.example.demo.global.enums;

public enum Region {
    GANGNAM("강남구"),
    GWANAK("관악구"),
    DONGDAEMUN("서울 동대문구"),
    MAPO("서울 마포구"),
    SEOCHO("서울 서초구"),
    YONGSAN("서울 용산구"),
    JONGNO("서울 종로구");

    private final String description;

    Region(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}