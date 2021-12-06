package com.example.misiones.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.misiones.dto.request.MissionRequest;
import com.example.misiones.model.Ship;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionValidationServiceImpl implements MissionValidationService {

	private final ShipService shipService;
	private final PlanetService planetService;
	private final CrewService crewService;
	
	@Override
	public boolean isValid(MissionRequest req) {
		if(req != null) {
		return hasShipAndStartDate(req)
				&& hasCaptainAndPlanet(req)
				&& hasShipPilot(req)
				&& hasRequiredCrew(req)
				&& hasRequiredPassengers(req)
				&& hasExclusiveCaptain(req)
				;
		}
		return false;
	}

	@Override
	public boolean hasShipAndStartDate(MissionRequest req) {
		// TODO validation
				return true;
	}

	@Override
	public boolean hasCaptainAndPlanet(MissionRequest req) {
		// TODO validation
				return true;
	}

	@Override
	public boolean hasShipPilot(MissionRequest req) {
		// TODO validation
				return true;
	}

	@Override
	public boolean hasRequiredCrew(MissionRequest req) {
		// TODO validation
				return true;
	}

	@Override
	public boolean hasRequiredPassengers(MissionRequest req) {
		// TODO validation
				return true;
	}

	@Override
	public boolean hasExclusiveCaptain(MissionRequest req) {
		// TODO validation
		return true;
	}

}
