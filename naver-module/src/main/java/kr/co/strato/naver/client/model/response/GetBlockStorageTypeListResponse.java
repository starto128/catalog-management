package kr.co.strato.naver.client.model.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GetBlockStorageTypeListResponse {
    private List<StorageType> storageTypeList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Builder
    @ToString
    public static class StorageType {
        private String code;
        private String name;
        private String storageMedia;
        private String iops;
        private String iopsUnit;
        private String minSize;
        private String minSizeUnit;
        private String maxSize;
        private String maxSizeUnit;
    }
}
