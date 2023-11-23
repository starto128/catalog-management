package kr.co.strato.core.domain.config.region.repository;

import kr.co.strato.core.domain.config.region.entity.ConfigRegion;
import kr.co.strato.core.domain.config.region.model.RegionSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomConfigRegionRepository {
    Page<ConfigRegion> findBySearchParam(Pageable pageable, RegionSearchParam searchParam);
}
