package com.example.misiones.dto.request;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PlanetRequest {
	private Long id;
	private String name;
	public BigDecimal diameter;
}
