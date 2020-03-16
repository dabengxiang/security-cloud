package com.onion.client.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: gyc
 * @Date: 2020/3/16 15:18
 */
@RestController
public class UserController  {


    @GetMapping("abc")
    public String  abc(){
        return "abc";
    }

}
