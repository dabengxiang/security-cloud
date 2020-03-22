package com.onion.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author gyc
 * @date 2020/3/14
 */

@PropertySource("classpath:onion-auth.properties")
@ConfigurationProperties(prefix = "onion.auth")
@Data
@SpringBootConfiguration
public class OnionAuthProperties {

    private OnionClientProperties[] clients;

    private int accessTokenValiditySeconds = 60*60*24;


    private int refreshTokenValiditySeconds = 60*60*24*7;

}
