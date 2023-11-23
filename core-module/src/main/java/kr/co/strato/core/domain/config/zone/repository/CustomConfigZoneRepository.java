package kr.co.strato.core.domain.config.zone.repository;

import kr.co.strato.core.domain.config.zone.entity.ConfigZone;
import kr.co.strato.core.domain.config.zone.model.ZoneSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomConfigZoneRepository {
    Page<ConfigZone> findBySearchParam(Pageable pageable, ZoneSearchParam searchParam);

}
