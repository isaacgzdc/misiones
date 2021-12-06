package com.example.misiones.dto.request;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.misiones.validation.AtLeastOneCaptainConstraint;
import com.example.misiones.validation.AtLeastOneConstraint;
import com.example.misiones.validation.ShipConstraint;

import lombok.Data;

@Data
public class MissionRequest {
	private Long id;
	@Size(min=2, message = "Mission name must be at least 2 characters long")
	@NotBlank
	private String name;
	@Future(message = "Mission start date must be in the future")
	@NotNull
	private LocalDateTime initDate;
	private LocalDateTime endDate;
	@ShipConstraint
	private ShipRequest ship;
	@AtLeastOneCaptainConstraint
	private Set<CrewRequest> captains = new HashSet<>();
	private Set<CrewRequest> crew = new HashSet<>();
	@AtLeastOneConstraint
	private Set<PlanetRequest> planets = new HashSet<>();
}
