package kr.co.strato.naver.client.model;

import kr.co.strato.core.domain.group.entity.Account;
import kr.co.strato.core.domain.group.model.CspAccountInfo;
import kr.co.strato.core.util.EncryptUtil;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NaverKeyDto {
    private String clientId;
    private String secret;
    private String accountId;
    private String companyUuid;
}

