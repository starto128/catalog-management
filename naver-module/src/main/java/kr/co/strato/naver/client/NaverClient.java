package kr.co.strato.naver.client;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import kr.co.strato.naver.client.model.NaverKeyDto;
import kr.co.strato.naver.client.model.response.*;
import kr.co.strato.naver.client.provider.NaverCredentialHeaderProvider;
import kr.co.strato.naver.client.provider.NaverRestTemplateProvider;
import kr.co.strato.naver.exception.FailToNaverQueryException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class NaverClient {
    private final String GET_REGION_LIST = "";
    private final String GET_ZONE_LIST = "";
    private final String GET_SERVER_IMAGE_LIST = "";
    private final String GET_SERVER_TYPE_LIST = "";
    private final String GET_BLOCK_STORAGE_LIST = "";
    private final String GET_K8S_IMAGE_LIST = "";
    private final String GET_PRODUCT_LIST = "";
    private final String GET_PRODUCT_PRICE_LIST = "";
    private final String GET_K8S_VERSION_LIST = "";

    private <T> ResponseEntity<T> getResponse(String url, String queryParam, String httpMethod, NaverKeyDto key, Class<T> responseType){
        if(queryParam == null){
            queryParam = "";
        }
        HttpHeaders headers = NaverCredentialHeaderProvider.getCredentialHeader(url, queryParam, httpMethod, key.getClientId(), key.getSecret());
        RestTemplate restTemplate = NaverRestTemplateProvider.getRestTemplate(headers);
        ResponseEntity<T> response = restTemplate.getForEntity(url + queryParam, responseType);

        return response;
    }

    private <T> T getBody(ResponseEntity<String> responseEntity, Type responseType, String fieldName){
        if(responseEntity == null || responseEntity.getBody() == null){
            return null;
        }
        String responseJson = responseEntity.getBody();
        Gson gson = new Gson();
        if(fieldName != null){
            JsonObject jsonObject = new JsonParser().parse(responseJson).getAsJsonObject();
            return gson.fromJson(jsonObject.get(fieldName), responseType);
        }
        return gson.fromJson(responseJson, responseType);
    }

    public GetRegionListResponse getRegions(NaverKeyDto naverKeyDto) throws FailToNaverQueryException {
        try{
            ResponseEntity<String> responseEntity = getResponse(GET_REGION_LIST, "?responseFormatType=json", "GET", naverKeyDto, String.class);
            GetRegionListResponse result = getBody(responseEntity, GetRegionListResponse.class, "getRegionListResponse");
            return result;
        }catch (Exception e){
            throw new FailToNaverQueryException(e);
        }
    }

    public GetZoneListResponse getZones(NaverKeyDto naverKeyDto, String regionCode) throws FailToNaverQueryException {
        try{
            String queryParam = "?regionCode=" + regionCode + "&responseFormatType=json";
            ResponseEntity<String> responseEntity = getResponse(GET_ZONE_LIST, queryParam, "GET", naverKeyDto, String.class);
            GetZoneListResponse result = getBody(responseEntity, GetZoneListResponse.class, "getZoneListResponse");
            return result;
        }catch (Exception e){
            throw new FailToNaverQueryException(e);
        }
    }

    public GetServerImageProductListResponse getVirtualServerImages(NaverKeyDto naverKeyDto, String regionCode) throws FailToNaverQueryException {
        try{
            String queryParam = "?regionCode=" + regionCode + "&responseFormatType=json";
            ResponseEntity<String> responseEntity = getResponse(GET_SERVER_IMAGE_LIST, queryParam, "GET", naverKeyDto, String.class);
            GetServerImageProductListResponse result = getBody(responseEntity, GetServerImageProductListResponse.class, "getServerImageProductListResponse");
            return result;
        }catch (Exception e){
            throw new FailToNaverQueryException(e);
        }
    }

    public GetServerProductListResponse getVirtualServerTypes(NaverKeyDto naverKeyDto, String regionCode, String imageCode) throws FailToNaverQueryException {
        try{
            String queryParam = "?regionCode="+regionCode+"&serverImageProductCode=" + imageCode + "&responseFormatType=json";
            ResponseEntity<String> responseEntity = getResponse(GET_SERVER_TYPE_LIST, queryParam, "GET", naverKeyDto, String.class);
            GetServerProductListResponse result = getBody(responseEntity, GetServerProductListResponse.class, "getServerProductListResponse");
            return result;
        }catch (Exception e){
            throw new FailToNaverQueryException(e);
        }
    }

    public List<GetK8sImageResponse> getK8sImages(NaverKeyDto naverKeyDto, String regionCode) throws FailToNaverQueryException {
        try{
            if(!"KR".equals(regionCode.toUpperCase())){
                return null;
            }
            String queryParam = "?responseFormatType=json";
            ResponseEntity<String> responseEntity = getResponse(GET_K8S_IMAGE_LIST, queryParam, "GET", naverKeyDto, String.class);
            List<GetK8sImageResponse> result = getBody(responseEntity, new TypeToken<List<GetK8sImageResponse>>(){}.getType(), null);
            return result;
        }catch (Exception e){
            throw new FailToNaverQueryException(e);
        }
    }

    public GetProductListResponse getSeverProducts(NaverKeyDto naverKeyDto, String regionCode) throws FailToNaverQueryException {
        try{
            String queryParam = "?regionCode=" + regionCode + "&productItemKindCode=VSVR&responseFormatType=json";
            ResponseEntity<String> responseEntity = getResponse(GET_PRODUCT_LIST, queryParam, "GET", naverKeyDto, String.class);
            return getBody(responseEntity, new TypeToken<GetProductListResponse>(){}.getType(), "getProductListResponse");
        }catch (Exception e){
            throw new FailToNaverQueryException(e);
        }
    }

    public GetProductPriceListResponse getServerProductPrices(NaverKeyDto naverKeyDto, String regionCode) throws FailToNaverQueryException {
        try{
            String queryParam = "?regionCode=" + regionCode + "&productItemKindCode=VSVR&responseFormatType=json";
            ResponseEntity<String> responseEntity = getResponse(GET_PRODUCT_PRICE_LIST, queryParam, "GET", naverKeyDto, String.class);
            System.out.println(responseEntity.getBody());
            return getBody(responseEntity, new TypeToken<GetProductPriceListResponse>(){}.getType(), "getProductPriceListResponse");
        }catch (Exception e){
            throw new FailToNaverQueryException(e);
        }
    }

    public List<GetK8sVersionResponse> getK8sVersion(NaverKeyDto naverKeyDto, String regionCode) throws FailToNaverQueryException {
        try{
            if(!"KR".equals(regionCode.toUpperCase())){
                return null;
            }
            String queryParam = "?responseFormatType=json";
            ResponseEntity<String> responseEntity = getResponse(GET_K8S_VERSION_LIST, queryParam, "GET", naverKeyDto, String.class);
            List<GetK8sVersionResponse> result = getBody(responseEntity, new TypeToken<List<GetK8sVersionResponse>>(){}.getType(), null);
            return result;
        }catch (Exception e){
            throw new FailToNaverQueryException(e);
        }
    }
}
