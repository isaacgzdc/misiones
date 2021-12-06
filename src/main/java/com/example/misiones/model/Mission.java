package com.example.misiones.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.misiones.config.JacksonConfig;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mission {

	private static final String SEQUENCE_NAME = "MISSION_SEQ";
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	@Size(min=2, message = "Mission name must be at least 2 characters long")
	@NotBlank
	private String name;
	@Future(message = "Mission start date must be in the future")
	@NotNull
	@JsonFormat(pattern = JacksonConfig.DATE_WITH_HOUR_FORMAT)
	private LocalDateTime initDate;
	@JsonFormat(pattern = JacksonConfig.DATE_WITH_HOUR_FORMAT)
	private LocalDateTime endDate;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "SHIP_ID")
	private Ship ship;
	
	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "MISSION_PLANET", 
        joinColumns = { @JoinColumn(name = "MISSION_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "PLANET_ID") }
    )
	private Set<Planet> planets = new HashSet<>();
	
	public void addPlanet(Planet planet) {
		this.planets.add(planet);
		planet.getMissions().add(this);
	}
	
	public void removePlanet(Planet planet) {
		this.planets.remove(planet);
		planet.getMissions().remove(this);
	}
	
}
