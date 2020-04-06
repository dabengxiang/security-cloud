package com.onion.controller;

import com.onion.properties.CaptchaProperties;
import com.onion.properties.OnionAuthProperties;
import com.onion.service.validateCode.ValidateCode;
import com.onion.service.validateCode.ValidateCodeService;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gyc
 * @date 2020/4/6
 */
@Controller
public class CaptchaController {



    @Autowired
    private ValidateCodeService validateCodeService;

    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        validateCodeService.create(request,response);

    }



    @RequestMapping("/validate")
    @ResponseBody
    public boolean validate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return validateCodeService.validate(request,response);

    }



}
