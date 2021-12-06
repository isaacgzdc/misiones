package com.example.misiones.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { ShipValidator.class})
public @interface ShipConstraint {

	String message() default "Mission cannot be created with provided data";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
