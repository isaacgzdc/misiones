package com.example.misiones.service;

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
	public Set<Planet> save(Set<PlanetRequest> planets) {
		// TODO Auto-generated method stub
		return null;
	}

}
