package com.example.misiones.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.misiones.dto.request.MissionRequest;
import com.example.misiones.model.Crew;
import com.example.misiones.model.Mission;
import com.example.misiones.model.Planet;
import com.example.misiones.model.Ship;
import com.example.misiones.model.enums.CrewType;
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
		Set<Planet> planets = planetService.savePlanets(req.getPlanets());
		Ship ship = shipService.save(req.getShip());
		if(ship != null) {
			Set<Crew> captains = crewService.saveCaptains(req.getCaptains());
			Set<Crew> crew = crewService.saveCrew(req.getCrew());
			if(captains != null && !captains.isEmpty()) {
				ship.getCrew().addAll(captains);
			}
			if(crew != null && !crew.isEmpty()) {
				ship.getCrew().addAll(crew);
			}
		}
		Mission mission = Mission.builder()
				.initDate(req.getInitDate())
				.name(req.getName())
				.planets(planets)
				.ship(ship)
				.build();
		missionRepository.save(mission);
		return mission;
		}
		return null;
	}

	private Mission transformToEntity(MissionRequest req) {
		Mission mission = Mission.builder()
				.initDate(req.getInitDate())
				.name(req.getName())
				.build();
		return mission;
	}

	@Override
	public Duration getMissionPeriod(Mission mission) {
		if(mission != null && mission.getId() != null) {
			Optional<Mission> missionDB = findById(mission.getId());
			if(missionDB.isPresent()) {
				BigDecimal total = BigDecimal.ONE;
				BigDecimal sumaDiametros = missionDB.get().getPlanets().stream()
				.map(p->p.getDiameter())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
				
				Set<Crew> basicCrew = missionDB.get().getShip().getCrew().stream()
				.filter(c-> !CrewType.CAPTAIN.equals(c.getType()))
				.collect(Collectors.toSet());
				
				for(Crew c : basicCrew) {
					total = total.add(BigDecimal.TEN.multiply(sumaDiametros));
				}
				
				Set<Crew> captainCrew = missionDB.get().getShip().getCrew().stream()
						.filter(c-> CrewType.CAPTAIN.equals(c.getType()))
						.collect(Collectors.toSet());
				
				for(Crew c : captainCrew) {
					total = total.add(new BigDecimal(100).multiply(sumaDiametros));
				}
				
				total = total.setScale(0, RoundingMode.UP);
				return Duration.ofHours(total.longValueExact());
				
			}
		}
		return Duration.ofHours(0);
	}
	
}
