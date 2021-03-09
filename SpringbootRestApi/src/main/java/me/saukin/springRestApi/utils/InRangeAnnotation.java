/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.saukin.springRestApi.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author saukin
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {InRangeValidator.class})
public @interface InRangeAnnotation {
    
    String message() default "Value is out of range";
    
    int min() default 1;
    
    int max() default 30;
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default{};
    
}
