//package com.onion.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//
///**
// * @author gyc
// * @date 2020/3/10
// */
//
//@Configuration
//@EnableResourceServer
//public class OnionResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().requestMatchers().antMatchers("/**")
//                .and().formLogin().loginPage("/login")
//                .and().authorizeRequests().antMatchers("/login").permitAll()
//                .and().authorizeRequests().antMatchers("/**").authenticated();
//
//    }
//
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
//    }
//}


