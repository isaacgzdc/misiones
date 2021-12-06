package com.example.misiones.service;

import java.util.Set;

import com.example.misiones.dto.request.PlanetRequest;
import com.example.misiones.model.Planet;

public interface PlanetService {
	Set<Planet> save(Set<PlanetRequest> planets);

}
