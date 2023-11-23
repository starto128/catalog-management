package kr.co.strato.naver.client.model.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetProductListResponse {
    private String requestId;
    private String returnCode;
    private String returnMessage;
    private Integer totalRows;
    private List<Product> productList;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class Product {
        private CommonCode productItemKind;
        private String productCode;
        private String productName;
        private String productDescription;

        @NoArgsConstructor
        @AllArgsConstructor
        @Getter
        @Setter
        @ToString
        public class CommonCode {
            private String code;
            private String codeName;
        }
    }
}
