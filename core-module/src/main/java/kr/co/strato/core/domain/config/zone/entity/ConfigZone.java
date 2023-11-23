package kr.co.strato.core.domain.config.zone.entity;

import kr.co.strato.core.domain.config.region.entity.ConfigRegion;
import kr.co.strato.core.domain.group.entity.CompanyUser;
import kr.co.strato.core.model.enums.CloudType;
import kr.co.strato.core.util.IdUtils;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class ConfigZone implements Serializable {
    private String uuid;

    private String code;

    private String name;

    private String regionCode;

    private ConfigRegion region;

    private String deletedYn;

    private CloudType cloudType;

    private String publicCloudYn;

    private Date createdAt;

    private String createdUserId;

    private CompanyUser companyUser;

    private Long controllerId;

    private String infraAlias;

    public void setDeletedYn(String deletedYn) {
        this.deletedYn = deletedYn;
    }

    @Builder
    private ConfigZone(String uuid, String code, String name, String regionCode, String deletedYn, CloudType cloudType, String publicCloudYn, Date createdAt, String createdUserId, Long controllerId, String infraAlias) {
        this.uuid = uuid;
        this.code = code;
        this.name = name;
        this.regionCode = regionCode;
        this.deletedYn = deletedYn;
        this.cloudType = cloudType;
        this.publicCloudYn = publicCloudYn;
        this.createdAt = createdAt;
        this.createdUserId = createdUserId;
        this.controllerId = controllerId;
        this.infraAlias = infraAlias;
    }


    public static ConfigZone.ConfigZoneBuilder builder(CloudType cloudType, String regionCode, String code){
        String uuid = "";
        return new ConfigZoneBuilder().cloudType(cloudType).code(code).regionCode(regionCode).uuid(uuid);
    }
}
