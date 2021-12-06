package com.example.misiones.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.misiones.dto.request.MissionRequest;
import com.example.misiones.model.Crew;
import com.example.misiones.model.Mission;
import com.example.misiones.model.Planet;
import com.example.misiones.model.Ship;
import com.example.misiones.repository.MissionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

	private final MissionRepository missionRepository;
	private final ShipService shipService;
	private final PlanetService planetService;
	private final CrewService crewService;
	private final MissionValidationService missionValidationService;
	
	@Override
	public List<Mission> findAll() {
		return missionRepository.findAll();
	}

	@Override
	public Optional<Mission> findById(Long id) {
		return missionRepository.findById(id);
	}
	
	@Override
	public Mission save(Mission mission) {
		return missionRepository.save(mission);
	}

	@Override
	public Mission update(Mission mission) {
		return missionRepository.save(mission);
	}

	@Override
	public void deleteById(Long id) {
		missionRepository.deleteById(id);
	}

	@Transactional
	@Override
	public Mission update(Long id, MissionRequest req) {
		if(req == null) {
			return null;
		}
		return missionRepository.save(transformToEntity(req));
	}

	@Override
	public Mission save(MissionRequest req) {
		if(missionValidationService.isValid(req)) {
			Mission mission = transformToEntity(req);
			missionRepository.save(mission);
		}
		return null;
	}

	private Mission transformToEntity(MissionRequest req) {
		Set<Planet> planets = planetService.save(req.getPlanets());
		Ship ship = shipService.save(req.getShip());
		Set<Crew> captains = crewService.saveCaptains(req.getCaptains());
		Set<Crew> crew = crewService.saveCrew(req.getCrew());
		ship.getCrew().addAll(captains);
		ship.getCrew().addAll(crew);
		Mission mission = Mission.builder()
				.initDate(req.getInitDate())
				.name(UUID.randomUUID().toString())
				.planets(planets)
				.ship(ship)
				.build();
		return mission;
	}
	
}
