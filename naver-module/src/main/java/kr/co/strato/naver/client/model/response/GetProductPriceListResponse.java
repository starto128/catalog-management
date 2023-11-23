package kr.co.strato.naver.client.model.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetProductPriceListResponse {
    private String requestId;
    private String returnCode;
    private String returnMessage;
    private Integer totalRows;
    private List<productPrice> productPriceList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class productPrice {
        private CommonCode productType;
        private Integer gpuCount;
        private Integer cpuCount;
        private Long memorySize;
        private Long baseBlockStorageSize;
        private String generationCode;
        private String productCode;
        private List<Price> priceList;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        @ToString
        public class Price {
            private String priceNo;
            private CommonCode priceType;
            private Region region;
            private CommonCode chargingUnitType;
            private CommonCode ratingUnitType;
            private String chargingUnitBasicValue;
            private CommonCode productRatingType;
            private CommonCode unit;
            private Long price;
            private CommonCode conditionType;
            private Long conditionPrice;
            private String priceDescription;
            private CommonCode meteringUnit;
            private String startDate;
            private CommonCode payCurrency;
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        @ToString
        public class CommonCode {
            private String code;
            private String codeName;
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        @ToString
        public class Region {
            private Integer regionNo;
            private String regionCode;
            private String regionName;
        }
    }
}
