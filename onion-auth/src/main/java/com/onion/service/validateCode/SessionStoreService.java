package com.onion.service.validateCode;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author gyc
 * @date 2020/4/6
 */
@Service
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
