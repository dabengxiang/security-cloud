package com.onion.exception;

/**
 * @Author: gyc
 * @Date: 2020/4/8 8:32
 */
public class ValidateCodeException extends RuntimeException {

    public ValidateCodeException(String message) {
        super(message);
    }
}
