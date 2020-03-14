package onion.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }


    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("read")
    public String read(){
        return "read";
    }



    @PreAuthorize("hasAuthority('user:add')")
    @PostMapping("add")
    public String add(){
        return "add";
    }
}