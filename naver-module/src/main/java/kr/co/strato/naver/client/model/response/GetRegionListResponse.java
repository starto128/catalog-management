package kr.co.strato.naver.client.model.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetRegionListResponse {
    private String requestId;
    private String returnCode;
    private String returnMessage;
    private Integer totalRows;
    private List<Region> regionList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Region {
        private String regionNo;
        private String regionCode;
        private String regionName;
    }
}
