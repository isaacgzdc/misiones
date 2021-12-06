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
@Constraint(validatedBy = { AtLeatOneCaptain.class})
public @interface AtLeastOneCaptainConstraint {
	String message() default "At least one captain in the collection is required";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
