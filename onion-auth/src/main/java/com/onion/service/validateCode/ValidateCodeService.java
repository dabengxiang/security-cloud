package com.onion.service.validateCode;

import com.onion.properties.CaptchaProperties;
import com.onion.properties.OnionAuthProperties;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gyc
 * @date 2020/4/6
 */
@Service
public class ValidateCodeService {



    @Autowired
    private OnionAuthProperties onionAuthProperties;


    @Autowired
    private ValidateCodeStoreService storeService;



    public void create(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Captcha captcha = null;

        CaptchaProperties captchaProperties = onionAuthProperties.getCaptchaProperties();

        if(StringUtils.equalsIgnoreCase("gif",captchaProperties.getType())){
            captcha = new GifCaptcha();
        }else{
            captcha = new SpecCaptcha();
        }

        captcha.setLen(captchaProperties.getLength());
        captcha.setWidth(captchaProperties.getWidth());
        captcha.setHeight(captchaProperties.getHeight());
        captcha.setCharType(captchaProperties.getCharType());

        String code = captcha.text().toLowerCase();

        storeService.store(request,response,new ValidateCode(code,captchaProperties.getExpire()));

        // 设置宽、高、位数
        CaptchaUtil.out(captcha, request, response);

    }



    public boolean validate(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String validateCode = request.getParameter("validateCode");
        if(validateCode!=null){
            return storeService.validate(request,response,validateCode);

        }

        return false;
    }


}
