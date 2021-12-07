package com.example.misiones.model;

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
@Table(uniqueConstraints = { @UniqueConstraint(name = "ship_name_unique", columnNames = { "name" }) })
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ship {

	private static final String SEQUENCE_NAME = "SHIP_SEQ";

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	@Size(min = 2, message = "Ship name must be at least 2 characters long")
	@NotBlank
	private String name;

	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "SHIP_CREW", joinColumns = { @JoinColumn(name = "SHIP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "CREW_ID") })
	private Set<Crew> crew = new HashSet<>();

	@Builder.Default
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "SHIP_PASSENGER", joinColumns = { @JoinColumn(name = "SHIP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PASSENGER_ID") })
	private Set<Passenger> passengers = new HashSet<>();

	public void addCrew(Crew crew) {
		this.crew.add(crew);
	}

	public void removeCrew(Crew crew) {
		this.crew.remove(crew);
	}

	public void addPassenger(Passenger pass) {
		this.passengers.add(pass);
	}

	public void removePassenger(Passenger pass) {
		this.passengers.remove(pass);
	}
}
