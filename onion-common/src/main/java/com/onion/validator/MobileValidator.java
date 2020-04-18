package com.onion.validator;

import com.onion.annotation.IsMobile;
import com.onion.entity.RegexpConstant;
import com.onion.utils.OnionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author gyc
 * @date 2020/4/18
 */

public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = RegexpConstant.MOBILE_REG;
                return OnionUtils.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}