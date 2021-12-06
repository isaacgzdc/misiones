package com.example.misiones.service;

import org.springframework.stereotype.Service;

import com.example.misiones.dto.request.ShipRequest;
import com.example.misiones.model.Ship;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {

	private final CrewService crewService;

	@Override
	public Ship save(ShipRequest ship) {
		// TODO Auto-generated method stub
		return null;
	}

}
