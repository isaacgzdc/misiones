package com.example.misiones.dto;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Value;

@Value
public class ShipDTO {
	private Long id;
	private UUID plate;
	private String name;
	private Set<MissionDTO> mission;
	private Set<CrewDTO> crew = new HashSet<>();
	private Set<PassengerDTO> passengers = new HashSet<>();
}
