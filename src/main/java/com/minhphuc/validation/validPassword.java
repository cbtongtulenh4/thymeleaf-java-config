package com.minhphuc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
// this annotation is defined the class that is going to validate our field
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Documented
public @interface validPassword {
    // this is the error message that showed in UI
    String message() default "Invalid Password";

    // this below is boilerplate code conform to Spring standard

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}
