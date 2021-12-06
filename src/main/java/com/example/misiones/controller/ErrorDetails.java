package com.example.misiones.controller;

import java.time.LocalDateTime;

import lombok.Value;

@Value
public class ErrorDetails {

	private LocalDateTime timestamp;
	private String detail;
	private String description;
}
