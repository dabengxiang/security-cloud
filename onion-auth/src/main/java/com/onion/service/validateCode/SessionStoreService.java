package com.onion.service.validateCode;

import com.onion.properties.OnionAuthProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author gyc
 * @date 2020/4/6
 */
@Service
@ConditionalOnProperty(prefix = "onion.auth.captchaProperties",name ="storeType",havingValue = "session")
public class SessionStoreService implements ValidateCodeStoreService {

    private final String STORE_KEY = "my_captcha";



    @Override
    public void store(HttpServletRequest request, HttpServletResponse response,ValidateCode validateCode) {
        HttpSession session = request.getSession();

            session.setAttribute(STORE_KEY,validateCode);

    }

    @Override
    public boolean validate(HttpServletRequest request, HttpServletResponse response,String code) {

        HttpSession session = request.getSession();
        ValidateCode attribute = (ValidateCode) session.getAttribute(STORE_KEY);

        if(attribute!= null && !attribute.isExpired()){
            return StringUtils.equalsIgnoreCase(code,attribute.getCode());
        }

        return false;
    }
}
