package com.example.misiones.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.misiones.dto.request.PlanetRequest;
import com.example.misiones.model.Planet;
import com.example.misiones.repository.PlanetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanetServiceImpl implements PlanetService {

	private final PlanetRepository planetRepository;

	@Override
	public Set<Planet> savePlanets(Set<PlanetRequest> planets) {
		Set<Planet> saved = new HashSet<>();
		if(planets != null && !planets.isEmpty()) {
			planets.forEach(p-> saved.add(planetRepository.save(transformToEntity(p))));
		}
		return saved;
	}

	private Planet transformToEntity(PlanetRequest p) {
		return Planet.builder()
				.name(p.getName())
				.diameter(p.getDiameter())
				.build();
	}

	@Override
	public boolean containsPlanet(Set<PlanetRequest> planets) {
		if(planets != null && !planets.isEmpty()) {
			planets.stream()
			.allMatch(p ->{
				return planetRepository.findById(p.getId())
						.map(c->Boolean.TRUE).orElse(Boolean.FALSE);
				
			});
		}
		return false;
	}

}
