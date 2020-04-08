package com.onion.service.validateCode;

import com.onion.properties.OnionAuthProperties;
import com.onion.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
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


    private final String  REDIS_VALIDATE_KEY = "validate_key";




    @Autowired
    private RedisService redisService;


    @Autowired
    private OnionAuthProperties onionAuthProperties;

    @Override
    public void store(HttpServletRequest request, HttpServletResponse response, ValidateCode validateCode) {
        if(request.getParameter(REDIS_VALIDATE_KEY)!=null){
            redisService.set(REDIS_VALIDATE_KEY,validateCode,validateCode.getExpire());

        }
        redisService.set(REDIS_VALIDATE_KEY+"_"+request.getSession().getId(),validateCode,validateCode.getExpire());

    }

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response, String code) {
        String key = null;
        if(request.getParameter(REDIS_VALIDATE_KEY)!=null){
            key = request.getParameter(REDIS_VALIDATE_KEY);
        }else{
            key = REDIS_VALIDATE_KEY + "_" + request.getSession();
        }
        ValidateCode validateCode = (ValidateCode) redisService.get(key);
        if(validateCode!=null && !validateCode.isExpired() ){
            if(StringUtils.equalsIgnoreCase(validateCode.getCode(),code)){
                return true;
            }
        }



        return false;
    }



}
