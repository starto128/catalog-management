package kr.co.strato.core.domain.config.region.model;

import kr.co.strato.core.model.enums.CloudType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegionSearchParam {
    private CloudType cloudType;
    private String deletedYn;
}
