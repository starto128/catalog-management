package kr.co.strato.core.domain.config.region.service;

import kr.co.strato.core.domain.config.region.entity.ConfigRegion;
import kr.co.strato.core.domain.config.region.model.RegionSearchParam;
import kr.co.strato.core.domain.config.region.repository.ConfigRegionRepository;
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
public class ConfigRegionDomainService {

    @Autowired
    private ConfigRegionRepository configRegionRepository;


    /**
     * 삭제되지 않은 리전 리스트 조회
     * @param pageable 페이징(optional)
     * @param searchParam 검색조건(optional)
     * @return
     */
    public Page<ConfigRegion> getRegionsNotDeleted(Pageable pageable, RegionSearchParam searchParam) {
        if(searchParam == null){
            searchParam = RegionSearchParam.builder().build();
        }
        searchParam.setDeletedYn(DeleteType.N.value());
        return configRegionRepository.findBySearchParam(pageable, searchParam);
    }

    public List<ConfigRegion> getRegions(RegionSearchParam searchParam) {
        if(searchParam == null){
            searchParam = RegionSearchParam.builder().build();
        }
        return configRegionRepository.findBySearchParam(null, searchParam).getContent();
    }

    @Transactional(rollbackFor = Exception.class)
    public void registerRegions(List<ConfigRegion> regions, CloudType cloudType){
        configRegionRepository.bulkUpdateDeletedYnByCloudType("Y", cloudType);
        configRegionRepository.saveAll(regions);
        configRegionRepository.deleteByCloudTypeAndDeletedYn(cloudType, "Y");
    }

    public List<ConfigRegion> getRegionsByCloudTypeAndAccountUuid(CloudType cloudType, String accountUuid) {
        return configRegionRepository.findByCloudTypeAndAccountUuidAndDeletedYn(cloudType, accountUuid, "N");
    }

    public List<ConfigRegion> getRegionListByCloudType(CloudType cloudType){
        return configRegionRepository.findByCloudTypeAndDeletedYn(cloudType, "N");
    }
}
