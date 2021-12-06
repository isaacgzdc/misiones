package com.example.misiones.service;

import org.springframework.stereotype.Service;

import com.example.misiones.dto.request.MissionRequest;

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
		return hasShipAndCaptain(req)
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
	public boolean hasShipAndCaptain(MissionRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCaptainAndPlanet(MissionRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasShipPilot(MissionRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRequiredCrew(MissionRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRequiredPassengers(MissionRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasExclusiveCaptain(MissionRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

}
