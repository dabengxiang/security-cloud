package com.onion.handle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author gyc
 * @date 2020/3/10
 */


@Configuration
public class AuthHandleServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired(required = false)
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired(required = false)
    private AccessDeniedHandler accessDeniedHandler;

    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        if(authenticationEntryPoint != null){
            resources.authenticationEntryPoint(authenticationEntryPoint);
        }
        if(accessDeniedHandler!=null){
            resources.accessDeniedHandler(accessDeniedHandler);
        }
    }


//    @Bean
//    public RequestInterceptor requestInterceptor(){
//
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate requestTemplate) {
//                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//                System.out.println(principal);
//            }
//        };
//
//
//    }

}


