package com.umc.training.domain.store.service;

import com.umc.training.domain.region.entity.Region;
import com.umc.training.domain.region.exception.RegionException;
import com.umc.training.domain.region.exception.code.RegionErrorCode;
import com.umc.training.domain.region.repository.RegionRepository;
import com.umc.training.domain.store.dto.request.StoreAddRequestDTO;
import com.umc.training.domain.store.entity.Store;
import com.umc.training.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class StoreService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    public void addStore(Long regionId, StoreAddRequestDTO request) {

        if (!regionRepository.existsById(regionId)) {
            throw new RegionException(RegionErrorCode.REGION_NOT_FOUND);
        }

        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new RegionException(RegionErrorCode.REGION_NOT_FOUND));

        // 가게 생성 및 저장
        Store store = Store.builder()
                .region(region)
                .name(request.name())
                .address(request.address())
                .build();

        storeRepository.save(store);
    }
}
