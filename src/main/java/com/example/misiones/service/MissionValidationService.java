package com.example.misiones.service;

import com.example.misiones.dto.request.MissionRequest;

public interface MissionValidationService {

	boolean isValid(MissionRequest req);
	
	//At least one ship and one captain for each mission
	boolean hasShipAndStartDate(MissionRequest req);
	
	// At least one or more captain and planets for each mission
	boolean hasCaptainAndPlanet(MissionRequest req);
	
	// If the ship had pilot, it must be in the current mission
	boolean hasShipPilot(MissionRequest req);
	
	// captains  + other crew >= ship crew
	boolean hasRequiredCrew(MissionRequest req);
	
	// captains  + other crew <= ship passengers
	boolean hasRequiredPassengers(MissionRequest req);
	
	// a captain assigned to only one mission at any given time
	boolean hasExclusiveCaptain(MissionRequest req);
}
