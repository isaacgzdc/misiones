package com.example.misiones.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import lombok.Value;

@Value
public class MissionDTO {
	private Long id;
	private String name;
	private LocalDateTime initDate;
	private LocalDateTime endDate;
	private ShipDTO ship;
	private Set<PlanetDTO> planets = new HashSet<>();
}
