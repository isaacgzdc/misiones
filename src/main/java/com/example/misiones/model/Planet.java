package com.example.misiones.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "planet_name_unique", columnNames = { "name" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Planet {

	private static final String SEQUENCE_NAME = "PLANET_SEQ";
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	@Size(min=2, message = "Planet must be at least 2 characters long")
	@NotBlank
	private String name;
	private BigDecimal diameter;
	
}
