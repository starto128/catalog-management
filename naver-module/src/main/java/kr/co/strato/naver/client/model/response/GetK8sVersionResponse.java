package kr.co.strato.naver.client.model.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetK8sVersionResponse {
    private String label;
    private String value;
}
