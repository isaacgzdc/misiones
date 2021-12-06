package com.example.misiones.validation;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class AtLeatOne implements ConstraintValidator<AtLeastOneConstraint, Set<?>>{

	@Override
	public boolean isValid(Set<?> value, ConstraintValidatorContext context) {
		if(value != null && !value.isEmpty()) {
			return true;
		}
		return false;
	}

	

}
