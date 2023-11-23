package kr.co.strato.core.domain.config.region.repository;

import kr.co.strato.core.domain.config.region.entity.ConfigRegion;
import kr.co.strato.core.model.enums.CloudType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConfigRegionRepository extends JpaRepository<ConfigRegion, String>, CustomConfigRegionRepository {
    List<ConfigRegion> findByCloudType(CloudType cloudType);
    List<ConfigRegion> findByDeletedYn(String deletedYn);
    List<ConfigRegion> findByCloudTypeAndDeletedYn(CloudType cloudType, String deletedYn);
    @Modifying
    @Query("update ConfigRegion r set r.deletedYn = :deletedYn where r.cloudType = :cloudType")
    int bulkUpdateDeletedYnByCloudType(String deletedYn, CloudType cloudType);
    int deleteByCloudTypeAndDeletedYn(CloudType cloudType, String deletedYn);

    List<ConfigRegion> findByCloudTypeAndAccountUuidAndDeletedYn(CloudType cloudType, String accountUuid, String deletedYn);
}
