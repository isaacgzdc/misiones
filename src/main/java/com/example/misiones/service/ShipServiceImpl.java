package com.example.misiones.service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.misiones.dto.request.CrewRequest;
import com.example.misiones.dto.request.ShipRequest;
import com.example.misiones.model.Ship;
import com.example.misiones.repository.ShipRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {

	private final ShipRepository shipRepository;

	@Override
	public Ship save(ShipRequest shipReq) {
		return shipRepository.save(transformToEntity(shipReq));
	}

	@Override
	public Optional<Ship> findById(Long id) {
		return shipRepository.findById(id);
	}

	private Ship transformToEntity(ShipRequest shipReq) {
		return Ship.builder()
				.name(shipReq.getName())
				.build();
	}

	@Override
	public boolean previousPilotInMission(ShipRequest shipReq, Set<CrewRequest> crew) {
		if(crew != null && !crew.isEmpty()) {
			Optional<Ship> s = findById(shipReq.getId());
			if(s.isPresent()) {
				Set<Long> set = s.get().getCrew().stream()
						.map(c->c.getId()).collect(Collectors.toSet());
				for(CrewRequest cr : crew) {
					if(set.contains(cr.getId())){
						return true;
					}
				}
				
			}
			
		}
		return false;
	}
}
