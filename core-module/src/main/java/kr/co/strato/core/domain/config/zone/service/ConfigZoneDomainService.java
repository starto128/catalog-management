package kr.co.strato.core.domain.config.zone.service;

import kr.co.strato.core.domain.config.zone.entity.ConfigZone;
import kr.co.strato.core.domain.config.zone.model.ZoneSearchParam;
import kr.co.strato.core.domain.config.zone.repository.ConfigZoneRepository;
import kr.co.strato.core.model.enums.CloudType;
import kr.co.strato.core.model.enums.DeleteType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class ConfigZoneDomainService {
    @Autowired
    private ConfigZoneRepository configZoneRepository;

    public Page<ConfigZone> getZonesNotDeleted(Pageable pageable, ZoneSearchParam searchParam){
        if(searchParam == null) {
            searchParam = ZoneSearchParam.builder().build();
        }
        searchParam.setDeletedYn(DeleteType.N.value());
        return configZoneRepository.findBySearchParam(pageable, searchParam);
    }

    public List<ConfigZone> getZonesNotDeleted(ZoneSearchParam searchParam){
        if(searchParam == null) {
            searchParam = ZoneSearchParam.builder().build();
        }
        searchParam.setDeletedYn(DeleteType.N.value());
        return configZoneRepository.findBySearchParam(null, searchParam).getContent();
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerZones(List<ConfigZone> zones, CloudType cloudType){
        configZoneRepository.bulkUpdateDeletedYnByCloudType("Y", cloudType);
        configZoneRepository.saveAll(zones);
        configZoneRepository.deleteByCloudTypeAndDeletedYn(cloudType,"Y");
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerZones(List<ConfigZone> zones, CloudType cloudType, String regionCode){
        configZoneRepository.bulkUpdateDeletedYnByCloudTypeAndRegionCode("Y", cloudType, regionCode);
        configZoneRepository.saveAll(zones);
        configZoneRepository.deleteByCloudTypeAndRegionCodeAndDeletedYn(cloudType, regionCode, "Y");
    }

    /**
     * 리전 정보에 의한 삭제되지 않은 존 리스트 조회
     * @param cloudType 클라우드 타입
     * @param regionCode 리전 정보
     * @return
     */
    public List<ConfigZone> getRegionsByCloudTypeAndRegionCode(CloudType cloudType, String regionCode) {
        return configZoneRepository.findByCloudTypeAndRegionCodeAndDeletedYn(cloudType, regionCode, "N");
    }
}
