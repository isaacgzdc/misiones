package com.example.misiones.service;

import java.util.Optional;
import java.util.Set;

import com.example.misiones.dto.request.CrewRequest;
import com.example.misiones.model.Crew;

public interface CrewService {

	Set<Crew> saveCaptains(Set<CrewRequest> captains);

	Set<Crew> saveCrew(Set<CrewRequest> crew);

	boolean containsCaptain(Set<CrewRequest> captains);

	Optional<Crew> findById(Long id);
}
