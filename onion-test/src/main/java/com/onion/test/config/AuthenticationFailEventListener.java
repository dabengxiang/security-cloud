package com.onion.test.config;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

/**
 * @Author: gyc
 * @Date: 2020/4/2 8:25
 */
@Component
public class AuthenticationFailEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent abstractAuthenticationFailureEvent) {
        System.out.println(abstractAuthenticationFailureEvent);
    }
}
