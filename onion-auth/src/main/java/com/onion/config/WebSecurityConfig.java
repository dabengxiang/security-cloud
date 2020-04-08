package com.onion.config;

import com.onion.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author gyc
 * @date 2020/3/9
 */

@EnableWebSecurity
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService onionUserDetailsService;


    @Autowired
    private ValidateCodeFilter validateCodeFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //让检验/oauth/**请求
//        http.csrf().disable().requestMatchers().antMatchers("/oauth/**").and()
//                .authorizeRequests().antMatchers("/oauth/**").authenticated();


        http.csrf().disable().authorizeRequests().antMatchers("/captcha","/validate").permitAll()
                .and().formLogin().and().httpBasic()
                .and().authorizeRequests().anyRequest().authenticated();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class);



    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication().withUser("nba").password("123456").roles("user:read");
        auth.userDetailsService(onionUserDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}