package kr.co.strato.naver.client.model.response;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GetK8sImageResponse {
    private String label;
    private String value;
}
