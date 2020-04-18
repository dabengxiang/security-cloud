package com.onion.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author gyc
 * @date 2020/3/13
 */

@RestController
public class TestController {

    @GetMapping("info")
    public String test(){
        return "febs-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("userRead")
    @PreAuthorize("hasAuthority('user:read')")
    public String userRead(){
        return "userRead";
    }

    @PreAuthorize("hasAuthority('user:write')")
    @GetMapping("userWrite")
    public String userWrite(){
        return "userWrite";
    }
}