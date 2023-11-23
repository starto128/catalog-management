package kr.co.strato.naver.client.provider;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NaverCredentialHeaderProvider {
    public static HttpHeaders getCredentialHeader(String requestUrl, String queryParam, String method, String clientId, String secret) {
        String space = " ";
        String newLine = "\n";
        String timestamp = Long.toString(System.currentTimeMillis());
        URI uri = URI.create(requestUrl);
        String joinUrl = Stream.of(uri.getPath(), queryParam)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(""));

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(joinUrl)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(clientId)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String encodeBase64String = Base64.encodeBase64String(rawHmac);

            if(queryParam == null){
                System.out.println("request URI: "+requestUrl);
            }else{
                System.out.println("request URI: "+requestUrl + queryParam);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.set("x-ncp-apigw-timestamp", timestamp);
            headers.set("x-ncp-iam-access-key", clientId);
            headers.set("x-ncp-apigw-signature-v2", encodeBase64String);

            return headers;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
