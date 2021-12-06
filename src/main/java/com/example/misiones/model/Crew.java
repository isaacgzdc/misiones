package com.example.misiones.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.example.misiones.model.enums.CrewType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Crew {

	private static final String SEQUENCE_NAME = "CREW_SEQ";

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	private Long id;
	private String name;
	@NonNull
	private CrewType type;

	@Builder.Default
	@ManyToMany(mappedBy = "crew", cascade = CascadeType.PERSIST)
	private Set<Ship> ships = new HashSet<>();

	public void addShip(Ship ship) {
		this.ships.add(ship);
		ship.getCrew().add(this);
	}

	public void removeShip(Ship ship) {
		this.ships.remove(ship);
		ship.getCrew().remove(this);
	}
}
