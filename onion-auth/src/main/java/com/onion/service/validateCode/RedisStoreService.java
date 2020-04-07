package com.onion.service.validateCode;

import com.onion.properties.OnionAuthProperties;
import com.onion.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: gyc
 * @Date: 2020/4/7 17:05
 */
@Service
//@ConditionalOnProperty(prefix = "onion.auth.captchaProperties",name ="storeType",havingValue = "redis")
@ConditionalOnExpression("'${onion.auth.captchaProperties.storeType}'.equals('redis')")
public class RedisStoreService implements ValidateCodeStoreService {


    private final String  REDIS_VALIDATE_CODE = "VALIDATE_CODE";



    @Autowired
    private RedisService redisService;

    @Autowired
    private OnionAuthProperties onionAuthProperties;

    @Override
    public void store(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode) {

        redisService.set(REDIS_VALIDATE_CODE+"_"+request.getSession().getId(),validateCode,validateCode.getExpire());

    }

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response, String code) {
        ValidateCode validateCode = (ValidateCode) redisService.get(REDIS_VALIDATE_CODE + "_" + request.getSession().getId());
        if(validateCode!=null && !validateCode.isExpired() ){
            if(StringUtils.equalsIgnoreCase(validateCode.getCode(),code)){
                return true;
            }
        }



        return false;
    }



}
