package com.umc.training.domain.region.repository;

import com.umc.training.domain.region.entity.Region;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface RegionRepository extends Repository<Region, Long> {
    
    Optional<Region> findById(Long id);
    
    boolean existsById(Long id);
    
    Region save(Region region);
}

