package com.example.exchange.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CurrencyValidation.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Currency {
    String message() default  "Not available currency";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
