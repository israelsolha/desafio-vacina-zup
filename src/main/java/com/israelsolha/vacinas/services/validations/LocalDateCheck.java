package com.israelsolha.vacinas.services.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = LocalDateCheckValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalDateCheck {

    String message() default "Datas devem ser no passado e no formato aaaa-mm-dd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
