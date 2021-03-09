/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.saukin.springRestApi.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author saukin
 */
public class InRangeValidator implements ConstraintValidator<InRangeAnnotation, Integer> { 
    
    private int min;
    private int max;

    @Override
    public void initialize(InRangeAnnotation constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(Integer t, ConstraintValidatorContext cvc) {
        return t == null || (t >= min && t <= max);
    }
    
}
