package com.jusfoun.web.validation;

import com.jusfoun.utils.IdcardUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdCardValidator implements ConstraintValidator<IdCardValid, String> {

    @Override
    public void initialize(IdCardValid constraintAnnotation) {

    }

    /**
     * 使用 IdCardUtils 直接验证
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        if (!IdcardUtils.validateCard(value)) {
            return false;
        }
        return true;
    }


}
