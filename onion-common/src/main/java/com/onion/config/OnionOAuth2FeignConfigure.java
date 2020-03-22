package com.onion.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

/**
 * @Author: gyc
 * @Date: 2020/3/20 13:56
 */
@Configuration
public class OnionOAuth2FeignConfigure {

    @Bean
    public RequestInterceptor requestInterceptor(){

        return new RequestInterceptor() {

            //取到tokenType和tokenValue，然后把他装到restTemplate的头部
            @Override
            public void apply(RequestTemplate requestTemplate) {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                Object details = authentication.getDetails();

                if (details instanceof OAuth2AuthenticationDetails) {
                    String tokenType = ((OAuth2AuthenticationDetails)details).getTokenType();
                    String tokenValue = ((OAuth2AuthenticationDetails)details).getTokenValue();
                    requestTemplate.header("Authorization",tokenType + " " + tokenValue);

                }




            }
        };

    }

}
