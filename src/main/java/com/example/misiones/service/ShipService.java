package com.example.misiones.service;

import java.util.Optional;
import java.util.Set;

import com.example.misiones.dto.request.CrewRequest;
import com.example.misiones.dto.request.ShipRequest;
import com.example.misiones.model.Ship;

public interface ShipService {

	Ship save(ShipRequest ship);

	Optional<Ship> findById(Long id);

	boolean previousPilotInMission(ShipRequest shipRequest, Set<CrewRequest> crew);

}
