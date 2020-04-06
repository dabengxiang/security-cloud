package com.onion.service.validateCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author gyc
 * @date 2020/4/6
 */
@Data
public class ValidateCode {
    private String code;

    private Long expire;

    private LocalDateTime expireTime;


    public ValidateCode(){

    }

    public ValidateCode(String code, Long expire) {
        this.code = code;
        this.expire = expire;
        this.expireTime = LocalDateTime.now().plusSeconds(expire);
    }

    @JsonIgnore
    public boolean isExpired() {
        return expireTime.isBefore(LocalDateTime.now());
    }
}

