package com.example.misiones.record;

import java.util.Set;
import java.util.UUID;

public record ShipRecord (
	Long id,
	UUID plate,
	String name,
	Set<MissionRecord> mission,
	Set<CrewRecord> crew,
	Set<PassengerRecord> passengers){
}
