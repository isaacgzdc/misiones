package com.example.misiones.dto.request;

import com.example.misiones.model.enums.CrewType;

import lombok.Data;

@Data
public class CrewRequest {
	private Long id;
	private String name;
	private CrewType type;
}
