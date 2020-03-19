package com.onion.handle;

import com.onion.utils.OnionUtils;
import com.onion.utils.ResultDto;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: gyc
 * @Date: 2020/3/19 10:49
 */
public class OnionAuthenticationEntryPoint implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
         OnionUtils.makeResponse(httpServletResponse, MediaType.APPLICATION_JSON_UTF8.toString(),
                HttpServletResponse.SC_UNAUTHORIZED, ResultDto.failure("token无效"));

    }



}
