package kr.co.strato.core.domain.config.zone.repository;

import kr.co.strato.core.domain.config.zone.entity.ConfigZone;
import kr.co.strato.core.model.enums.CloudType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConfigZoneRepository extends JpaRepository<ConfigZone, String>, CustomConfigZoneRepository {
    List<ConfigZone> findByCloudType(CloudType cloudType);
    @Modifying
    @Query("update ConfigZone z set z.deletedYn = :deletedYn where z.cloudType = :cloudType")
    int bulkUpdateDeletedYnByCloudType(String deletedYn, CloudType cloudType);
    int deleteByCloudTypeAndDeletedYn(CloudType cloudType, String deletedYn);
    @Modifying
    @Query("update ConfigZone z set z.deletedYn = :deletedYn where z.cloudType = :cloudType and z.regionCode = :regionCode")
    int bulkUpdateDeletedYnByCloudTypeAndRegionCode(String deletedYn, CloudType cloudType, String regionCode);
    int deleteByCloudTypeAndRegionCodeAndDeletedYn(CloudType cloudType, String regionCode, String deletedYn);

    List<ConfigZone> findByCloudTypeAndRegionCodeAndDeletedYn(CloudType cloudType, String regionCode, String deletedYn);
}
