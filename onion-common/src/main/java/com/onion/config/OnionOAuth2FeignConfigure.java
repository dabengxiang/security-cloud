package com.onion.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: gyc
 * @Date: 2020/3/20 13:56
 */
@Configuration
public class OnionOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor requestInterceptor(){

        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                System.out.println(principal);
            }
        };

    }

}
