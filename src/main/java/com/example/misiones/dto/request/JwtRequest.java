package com.example.misiones.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest implements Serializable {
	private static final long serialVersionUID = -5288326978552401012L;
	private String username;
	private String password;
}
