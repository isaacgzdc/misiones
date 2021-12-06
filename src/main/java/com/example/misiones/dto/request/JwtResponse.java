package com.example.misiones.dto.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = 1408739489520393841L;
	private final String token;
}
