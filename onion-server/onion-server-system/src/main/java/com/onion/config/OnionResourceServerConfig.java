package com.onion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author gyc
 * @date 2020/3/10
 */

@Configuration
@EnableResourceServer
public class OnionResourceServerConfig extends ResourceServerConfigurerAdapter {




    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html","/webjars/**","/swagger-resources/**","/v2/api-docs/**").permitAll()
                .antMatchers("/**").authenticated();    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }






}


