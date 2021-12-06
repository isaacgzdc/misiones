package com.example.misiones.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class JacksonConfig {
	
	public static final String DATE_WITH_HOUR_FORMAT = "yyyy-MM-dd'T'hh:mm";
	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSS";
	
	@Bean
	@Primary
	public ObjectMapper objectMapper() {
		return JsonMapper.builder()
				.addModule(new JavaTimeModule())
				.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)
				.build();
	}
	
}
