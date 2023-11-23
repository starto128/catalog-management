package kr.co.strato.core.domain.config.region.entity;

import kr.co.strato.core.domain.group.entity.CompanyUser;
import kr.co.strato.core.model.enums.CloudType;
import kr.co.strato.core.util.IdUtils;
import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class ConfigRegion implements Serializable {
    private String uuid;

    private String code;

    private String name;

    private String deletedYn;

    private CloudType cloudType;

    private String publicCloudYn;

    private Date createdAt;

    private String createdUserId;

    private String accountUuid;


    public void setDeletedYn(String deletedYn) {
        this.deletedYn = deletedYn;
    }

    @Builder
    public ConfigRegion(String uuid, String name, String deletedYn, CloudType cloudType, String publicCloudYn, Date createdAt, String createdUserId, String accountUuid, String code) {
        this.uuid = uuid;
        this.name = name;
        this.deletedYn = deletedYn;
        this.cloudType = cloudType;
        this.publicCloudYn = publicCloudYn;
        this.createdAt = createdAt;
        this.createdUserId = createdUserId;
        this.accountUuid = accountUuid;
        this.code = code;
    }

    public static ConfigRegionBuilder builder(CloudType cloudType, String code){
        String uuid = "";
        return new ConfigRegionBuilder().cloudType(cloudType).code(code).uuid(uuid);
    }
}
