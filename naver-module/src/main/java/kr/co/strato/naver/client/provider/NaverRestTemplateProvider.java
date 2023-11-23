package kr.co.strato.naver.client.provider;

import kr.co.strato.naver.config.NaverRestTemplateInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class NaverRestTemplateProvider implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        NaverRestTemplateProvider.applicationContext = applicationContext;
    }

    public static RestTemplate getRestTemplate(HttpHeaders headers){
        RestTemplate restTemplate = applicationContext.getBean("naverRestTemplate", RestTemplate.class);
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(NaverRestTemplateInterceptor.getInstance(headers));
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
}