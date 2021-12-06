package com.example.misiones.record;

import java.time.LocalDateTime;
import java.util.Set;

public record MissionRecord(
	Long id,
	String name,
	LocalDateTime initDate,
	LocalDateTime endDate,
	ShipRecord ship,
	Set<PlanetRecord> planets) { 
	
}
