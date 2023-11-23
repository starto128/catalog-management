package kr.co.strato.naver.client.model.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetStorageProductPriceListResponse {
    private String requestId;
    private String returnCode;
    private String returnMessage;
    private Integer totalRows;
    private List<ProductPriceList> productPriceList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class ProductPriceList {
        private CommonCode productType;
        private CommonCode diskType;
        private CommonCode diskDetailType;
        private CommonCode productItemKind;
        private CommonCode productItemKindDetail;
        private String productCode;
        private String productDescription;
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
            private Double price;
            private CommonCode conditionType;
            private Long conditionPrice;
            private String priceDescription;
            private CommonCode meteringUnit;
            private String startDate;
            private CommonCode payCurrency;
            private Integer freeValue;
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
