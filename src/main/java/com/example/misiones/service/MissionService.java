package com.example.misiones.service;

import java.util.List;
import java.util.Optional;

import com.example.misiones.dto.request.MissionRequest;
import com.example.misiones.model.Mission;

public interface MissionService {
	List<Mission> findAll();
	Optional<Mission> findById(Long id);
	Mission save(Mission mission);
	Mission update(Mission mission);
	Mission update(Long id, MissionRequest dto);
	void deleteById(Long id);
	Mission save(MissionRequest req);
}
