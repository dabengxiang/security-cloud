package com.onion;

import com.onion.handle.EnableOnionAuthExceptionHandle;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author gyc
 * @date 2020/3/13
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableOnionAuthExceptionHandle
@MapperScan(basePackages = "com.onion.mapper" )
public class SystemApp {

    public static void main(String[] args) {
        SpringApplication.run(SystemApp.class,args);
    }

}
