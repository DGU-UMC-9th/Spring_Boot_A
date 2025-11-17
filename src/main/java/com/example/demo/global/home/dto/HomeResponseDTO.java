package com.example.demo.global.home.dto;

import com.example.demo.global.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class HomeResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HomeDTO {
        private Integer point;
        private Region currentLocation;
        private Long currentMissions;
        private Integer targetMissions;
        private Integer bonusPoint;
        private List<AvailableMissionDTO> availableMissions;
        private PageInfoDTO pageInfo;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AvailableMissionDTO {
        private String storeName;
        private String category;
        private String content;
        private String pointText;
        private String dDay;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PageInfoDTO {
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }
}