package com.onion.handle;

import com.onion.utils.OnionUtils;
import com.onion.utils.ResultDto;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: gyc
 * @Date: 2020/3/19 10:59
 */
public class OnionAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        OnionUtils.makeResponse(httpServletResponse, MediaType.APPLICATION_JSON_UTF8.toString(),
                HttpServletResponse.SC_FORBIDDEN, ResultDto.failure("没有相应的权限访问"));
    }
}
