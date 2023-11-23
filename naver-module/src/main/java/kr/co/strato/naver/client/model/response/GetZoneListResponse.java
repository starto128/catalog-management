package kr.co.strato.naver.client.model.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetZoneListResponse {
    private String requestId;
    private String returnCode;
    private String returnMessage;
    private List<Zone> zoneList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Zone {
        private String zoneNo;
        private String zoneName;
        private String zoneCode;
        private String zoneDescription;
        private String regionNo;
    }
}
