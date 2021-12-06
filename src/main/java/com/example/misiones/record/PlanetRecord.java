package com.example.misiones.record;

import java.math.BigDecimal;

public record PlanetRecord (
	Long id,
	String name,
	BigDecimal diameter) {
}
