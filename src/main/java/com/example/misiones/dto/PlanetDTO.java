package com.example.misiones.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Value;

@Value
public class PlanetDTO {
	private Long id;
	private String name;
	private BigDecimal diameter;
}
