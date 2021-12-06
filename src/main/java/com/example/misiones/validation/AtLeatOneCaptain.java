package com.example.misiones.validation;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

import com.example.misiones.dto.request.CrewRequest;
import com.example.misiones.model.enums.CrewType;

@Component
public class AtLeatOneCaptain implements ConstraintValidator<AtLeastOneCaptainConstraint, Set<CrewRequest>>{

	@Override
	public boolean isValid(Set<CrewRequest> value, ConstraintValidatorContext context) {
		if(value != null && !value.isEmpty()) {
			return value.stream()
			.anyMatch(c->CrewType.CAPTAIN.equals(c.getType()));
		}
		return false;
	}

	

}
