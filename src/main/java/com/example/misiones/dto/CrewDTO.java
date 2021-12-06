package com.example.misiones.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.misiones.model.enums.CrewType;

import lombok.Value;

@Value
public class CrewDTO {
	private Long id;
	private String name;
	private CrewType type;
	private Set<ShipDTO> ships = new HashSet<>();
}
