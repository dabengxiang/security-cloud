package com.onion;

import com.onion.handle.EnableOnionAuthExceptionHandle;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gyc
 * @date 2020/3/1
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
//@EnableOnionAuthExceptionHandle
@MapperScan("com.onion.mapper")
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }



    @GetMapping("/getUser")
    public String getUser(){
        return  "getUser";
    }
}
