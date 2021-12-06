package com.example.misiones;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.example.misiones.dto.request.CrewRequest;
import com.example.misiones.dto.request.MissionRequest;
import com.example.misiones.dto.request.PlanetRequest;
import com.example.misiones.dto.request.ShipRequest;
import com.example.misiones.model.Crew;
import com.example.misiones.model.Mission;
import com.example.misiones.model.Passenger;
import com.example.misiones.model.Planet;
import com.example.misiones.model.Ship;
import com.example.misiones.model.enums.CrewType;

public final class DataForTest {

	private DataForTest() { /*no-arg*/ }
	
	public static Mission simpleMission(String name, LocalDateTime startDate) {
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
				.plate(UUID.randomUUID())
				.build();
		
		
		return Mission.builder()
				.name(name)
				.initDate(startDate)
				.endDate(startDate.plusYears(3))
				.ship(ship)
				.planets(planetSet)
				.build();
		
	
	}
	
	public static Mission simpleMission() {
		return simpleMission("From earth to mars", LocalDateTime.now().plusYears(1));
	}

	public static MissionRequest missionRequest(String string, LocalDateTime plusDays) {
		Set<CrewRequest> captains = new HashSet<>();
		CrewRequest captain = new CrewRequest();
		captain.setName("Patricia");
		captain.setType(CrewType.CAPTAIN);
		captains.add(captain);
		
		Set<CrewRequest> crew = new HashSet<>();
		CrewRequest pilot = new CrewRequest();
		pilot.setName("Cassandra");
		pilot.setType(CrewType.PILOT);
		crew.add(pilot);
		
		Set<PlanetRequest> planets = new HashSet<>();
		PlanetRequest planet = new PlanetRequest();
		planet.setName("PLT-01");
		planets.add(planet);
		
		ShipRequest ship = new ShipRequest();
		ship.setName("TEST-001");

		MissionRequest request = new MissionRequest();
		request.setName(string);
		request.setInitDate(plusDays);
		request.setCaptains(captains);
		request.setCrew(crew);
		request.setEndDate(request.getInitDate().plusYears(2));
		request.setPlanets(planets);
		request.setShip(ship);
		
		return request;
	}
}
