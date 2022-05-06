package com.vheal.entity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailContraintValidator.class)
public @interface UniqueEmail {

    //    define default error message
    public String message() default "must have unique value";

    //    define default groups
    public Class<?>[] groups() default {};

    //    define default payloads
    public Class<? extends Payload>[] payload() default{};
}