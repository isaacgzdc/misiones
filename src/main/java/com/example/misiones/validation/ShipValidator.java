package com.example.misiones.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.example.misiones.dto.request.ShipRequest;

@Component
public class ShipValidator implements ConstraintValidator<ShipConstraint, ShipRequest>{

	@Override
	public void initialize(ShipConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	
	@Override
	public boolean isValid(ShipRequest value, ConstraintValidatorContext context) {
		if(value != null) {
			return true;
		}
		return false;
	}
	

}
