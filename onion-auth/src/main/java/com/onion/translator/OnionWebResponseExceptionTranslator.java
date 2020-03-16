package com.onion.translator;

import com.utils.ResultDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @Author: gyc
 * @Date: 2020/3/16 15:30
 */

public class OnionWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) throws Exception {

        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);

        if(e instanceof  InvalidGrantException ){

            String message = "";

            if (StringUtils.containsIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                message = "refresh token无效";
                return builder.body(ResultDto.failure(message));
            }
            if (StringUtils.containsIgnoreCase(e.getMessage(), "locked")) {
                message = "用户已被锁定，请联系管理员";
                return builder.body(ResultDto.failure(message));
            }
            message = "用户名或密码错误";
            return builder.body(ResultDto.failure(message));

        }

        if(e instanceof UnsupportedGrantTypeException){

            return builder.body(ResultDto.failure("不支持该验证方式"));


        }

        return builder.body(ResultDto.failure("服务器错误"));



    }
}
