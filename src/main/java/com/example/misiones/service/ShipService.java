package com.example.misiones.service;

import com.example.misiones.dto.request.ShipRequest;
import com.example.misiones.model.Ship;

public interface ShipService {

	Ship save(ShipRequest ship);

}
