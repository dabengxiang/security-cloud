package com.onion.controller;

import com.utils.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author gyc
 * @date 2020/3/13
 */
@RestController
@RequestMapping
public class SecurityController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("/user")
    public Principal currentUser(Principal principal){
        return principal;
    }


    @GetMapping("/user1")
    public Map<String, Object> currentUser1(Principal principal){

        HashMap<String, Object> map = new HashMap<>();
        map.put("name","user");
        return map;

    }

    @PostMapping("/signup")
    public ResultDto<String> signup(HttpServletRequest httpServletRequest){
        String authorization  = httpServletRequest.getHeader("Authorization");
        String token = authorization.replace("bearer" , "");

        if(consumerTokenServices.revokeToken(token)){
            return ResultDto.success("退出成功");
        }else{
            return ResultDto.failure("退出失败");

        }


    }




}
