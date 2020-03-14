package com.onion.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gyc
 * @date 2020/3/9
 */
@RestController
@RequestMapping
public class UserController {


    @GetMapping("/getUser")
    public String getUser(){
        return "getUser";
    }

}
