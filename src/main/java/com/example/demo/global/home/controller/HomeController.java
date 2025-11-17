package com.example.demo.global.home.controller;

import com.example.demo.global.enums.Region;
import com.example.demo.global.home.dto.HomeResponseDTO;
import com.example.demo.global.home.service.HomeQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final HomeQueryService homeQueryService;

    // 홈 화면 조회 GET /api/home?memberId=1&region=GANGNAM&page=0&size=10
    @GetMapping
    public HomeResponseDTO.HomeDTO getHome(
            @RequestParam Long memberId,
            @RequestParam Region region,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return homeQueryService.getHomeInfo(
                memberId,
                region,
                PageRequest.of(page, size)
        );
    }
}