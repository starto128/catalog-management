package kr.co.strato.naver.client.model.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetServerImageProductListResponse {
    private String requestId;
    private String returnCode;
    private String returnMessage;
    private List<Product> productList;
    private Integer totalRows;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Product {
        private String productCode;
        private String productName;
        private CommonCode productType;
        private String productDescription;
        private CommonCode infraResourceType;
        private Integer cpuCount;
        private Long memorySize;
        private Long baseBlockStorageSize;
        private CommonCode platformType;
        private String osInformation;
        private String dbKindCode;
        private Long addBlockStorageSize;
        private String generationCode;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        @ToString
        public static class CommonCode {
            private String code;
            private String codeName;
        }
    }
}
