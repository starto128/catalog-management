package kr.co.strato.core.domain.config.zone.model;

import kr.co.strato.core.model.enums.CloudType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ZoneSearchParam {
    private CloudType cloudType;
    private String regionCode;
    private String deletedYn;
}
