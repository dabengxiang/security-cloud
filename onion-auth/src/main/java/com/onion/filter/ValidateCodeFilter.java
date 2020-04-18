package com.onion.filter;

import com.onion.exception.ValidateCodeException;
import com.onion.service.validateCode.ValidateCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author gyc
 * @date 2020/4/7
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {


    @Autowired
    private ValidateCodeService validateCodeStoreService;


    private static List<RequestMatcher> list = null;


    private final String VALICATE_KEY = "VALICATE_KEY";


    static {
        list = new ArrayList<>();
        list.add(new AntPathRequestMatcher("/oauth/token", HttpMethod.POST.toString()));
        list.add(new AntPathRequestMatcher("/login", HttpMethod.POST.toString()));
    }




    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


//        for (RequestMatcher requestMatcher : list) {
//
//            if(requestMatcher.matches(httpServletRequest)){
//                if(!validateCodeStoreService.validate(httpServletRequest,httpServletResponse)){
//                    throw  new ValidateCodeException("验证码错误");
//                }
//
//            }
//        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }



}
