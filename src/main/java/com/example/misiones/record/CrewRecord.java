package com.example.misiones.record;

import java.util.Set;

import com.example.misiones.model.enums.CrewType;

public record CrewRecord (
	Long id,
	String name,
	CrewType type,
	Set<ShipRecord> ships){
}
