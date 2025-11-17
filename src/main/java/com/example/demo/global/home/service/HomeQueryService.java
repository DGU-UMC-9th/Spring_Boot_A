package com.example.demo.global.home.service;

import com.example.demo.global.home.dto.HomeResponseDTO;
import com.example.demo.global.enums.Region;
import org.springframework.data.domain.Pageable;

public interface HomeQueryService {
    HomeResponseDTO.HomeDTO getHomeInfo(Long memberId, Region region, Pageable pageable);
}