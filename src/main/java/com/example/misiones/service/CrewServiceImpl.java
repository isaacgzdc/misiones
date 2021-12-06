package com.example.misiones.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.misiones.dto.request.CrewRequest;
import com.example.misiones.model.Crew;
import com.example.misiones.model.enums.CrewType;
import com.example.misiones.repository.CrewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrewServiceImpl implements CrewService {

	private final CrewRepository crewRepository;
	
	@Override
	public Set<Crew> saveCaptains(Set<CrewRequest> captains) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Crew> saveCrew(Set<CrewRequest> crew) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean containsCaptain(Set<CrewRequest> captains) {
		if(captains != null && !captains.isEmpty()) {
			captains.stream()
			.anyMatch(crew ->{
				return CrewType.CAPTAIN.equals(crew.getType())
						&& crewRepository.findById(crew.getId())
						.map(c->Boolean.TRUE).orElse(Boolean.FALSE);
				
			});
		}
		return false;
	}

	@Override
	public Optional<Crew> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
