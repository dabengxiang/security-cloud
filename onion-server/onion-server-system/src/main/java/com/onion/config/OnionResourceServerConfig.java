package com.onion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author gyc
 * @date 2020/3/10
 */

@Configuration
@EnableResourceServer
public class OnionResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired(required = false)
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired(required = false)
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().requestMatchers().antMatchers("/**").and().authorizeRequests().antMatchers("/**").authenticated();
    }


//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        if(authenticationEntryPoint != null){
//            resources.authenticationEntryPoint(authenticationEntryPoint);
//        }
//        if(accessDeniedHandler!=null){
//            resources.accessDeniedHandler(accessDeniedHandler);
//
//        }
//    }
}


