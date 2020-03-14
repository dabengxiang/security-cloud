package com.onion.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializerBeans;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author gyc
 * @date 2020/2/28
 */
@SpringBootApplication
@EnableEurekaServer
public class RegisterApp {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApp.class,args);





    }
}
