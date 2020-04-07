package com.onion.test.config;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: gyc
 * @Date: 2020/4/2 8:17
 */
@Component
public class AuthenticationSuccessEventListener  implements ApplicationListener<AuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        System.out.println(authenticationSuccessEvent);
    }
}
