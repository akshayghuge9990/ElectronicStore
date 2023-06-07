package com.electronicstore.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid,String> {

private static Logger log = LoggerFactory.getLogger(ImageNameValidator.class);


    /*
     * @author Akshay
     * @Field It is custom Validation field is  used to  validation purpose
     * @param
     * @param
     * @return boolean
     */

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        log.info(" massage from isvalid :{}",value,context);

        if (value.isBlank()) {
            return false;
        }else {
            return true;
        }

    }
}
