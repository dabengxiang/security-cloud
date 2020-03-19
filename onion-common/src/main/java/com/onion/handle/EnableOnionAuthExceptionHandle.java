package com.onion.handle;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Author: gyc
 * @Date: 2020/3/19 11:36
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Import(OnionAuthExceptionConfigure.class)
public @interface EnableOnionAuthExceptionHandle {
}
