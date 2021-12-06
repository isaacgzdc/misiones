package com.example.misiones;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.misiones.model.Crew;
import com.example.misiones.model.Mission;
import com.example.misiones.model.Passenger;
import com.example.misiones.model.Planet;
import com.example.misiones.model.Ship;
import com.example.misiones.model.enums.CrewType;
import com.example.misiones.repository.MissionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class MisionesEspacialesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MisionesEspacialesApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(MissionRepository missionRepository, ObjectMapper mapper) {
		
		return args -> {
			Set<Planet> planetSet = new HashSet<>();
			Planet planet = Planet.builder()
					.name("Earth")
					.diameter(new BigDecimal(12750000))
					.build();
			planetSet.add(planet);
			planet = Planet.builder()
					.name("Mars")
					.diameter(new BigDecimal(6790000))
					.build();
			planetSet.add(planet);
			
			Set<Crew> crewSet = new HashSet<>();
			Crew crew = Crew.builder()
					.name("Brais")
					.type(CrewType.CAPTAIN)
					.build();
			crewSet.add(crew);
			crew = Crew.builder()
					.name("Violeta")
					.type(CrewType.PILOT)
					.build();
			crewSet.add(crew);
			
			Set<Passenger> passengerSet = new HashSet<>();
			Passenger passenger = Passenger.builder()
					.name("Xoel")
					.build();
			passengerSet.add(passenger);
			passenger = Passenger.builder()
					.name("Uxia")
					.build();
			passengerSet.add(passenger);
			
			Ship ship = Ship.builder()
					.crew(crewSet)
					.passengers(passengerSet)
					.name("EARTH-2100-JVL")
					.build();
			
			Mission mission1 = Mission.builder()
					.name("From earth to mars")
					.initDate(LocalDateTime.now().plusYears(1))
					.endDate(LocalDateTime.now().plusYears(3))
					.ship(ship)
					.planets(planetSet)
					.build();
			
			log.debug( mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mission1));
			
			missionRepository.save(mission1);
		};
	}
}
