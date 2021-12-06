package com.example.misiones.controller;

import java.time.LocalDateTime;

import com.example.misiones.config.JacksonConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Value;

@Value
public class ErrorDetails {
	@JsonFormat(pattern = JacksonConfig.TIMESTAMP_FORMAT)
	private LocalDateTime timestamp;
	private String detail;
	private String description;
}
