//package com.onion.register;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author gyc
// * @date 2020/3/1
// */
//@EnableWebSecurity
//public class OnionRegisterWebSecurityConfigure extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().ignoringAntMatchers("/eureka/**");
//        super.configure(http);
//
//    }
//}
