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
@Constraint(validatedBy = { AtLeatOne.class})
public @interface AtLeastOneConstraint {
	String message() default "At least one item in the collection is required";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
