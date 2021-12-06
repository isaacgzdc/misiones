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
		Optional<Ship> ship = shipService.findById(req.getShip().getId());
		return ship.isPresent() && req.getInitDate() != null;
	}

	@Override
	public boolean hasCaptainAndPlanet(MissionRequest req) {
		boolean captain= crewService.containsCaptain(req.getCaptains());
		boolean planet = planetService.containsPlanet(req.getPlanets());
		return captain && planet;
	}

	@Override
	public boolean hasShipPilot(MissionRequest req) {
		boolean pilot = shipService.previousPilotInMission(req.getShip(),req.getCrew());
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
