package com.example.misiones.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.misiones.model.Crew;
import com.example.misiones.model.Mission;
import com.example.misiones.model.Passenger;
import com.example.misiones.model.Planet;
import com.example.misiones.model.Ship;
import com.example.misiones.model.enums.CrewType;
import com.example.misiones.repository.MissionRepository;

@ExtendWith(MockitoExtension.class)
class MissionServiceImplTest {

	@Mock
    private MissionRepository missionRepository;

    @InjectMocks
	private MissionServiceImpl missionService;

	@Test
	void testFindAll() {
		List<Mission> missions = missions();
		Mockito.when(missionRepository.findAll()).thenReturn(missions );
		List<Mission> result = missionService.findAll();
		assertTrue(result.size() == 1);
	}

	@Test
	void testFindById() {
		Mission mission = missions().get(0);
		mission.setId(1L);
		Mockito.when(missionRepository.findById(mission.getId())).thenReturn(Optional.of(mission));
		Optional<Mission> result = missionService.findById(mission.getId());
		assertTrue(result.isPresent());
		assertEquals(mission,result.get());
	}

	@Test
	void testSaveMissionRequest() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveMission() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateMission() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateLongMissionDTO() {
		fail("Not yet implemented");
	}

	private List<Mission> missions() {
		Set<Planet> planetSet = new HashSet<>();
		Planet planet = Planet.builder().name("Earth").diameter(new BigDecimal(12750000)).build();
		planetSet.add(planet);
		planet = Planet.builder().name("Mars").diameter(new BigDecimal(6790000)).build();
		planetSet.add(planet);

		Set<Crew> crewSet = new HashSet<>();
		Crew crew = Crew.builder().name("Brais").type(CrewType.CAPTAIN).build();
		crewSet.add(crew);
		crew = Crew.builder().name("Violeta").type(CrewType.PILOT).build();
		crewSet.add(crew);

		Set<Passenger> passengerSet = new HashSet<>();
		Passenger passenger = Passenger.builder().name("Xoel").build();
		passengerSet.add(passenger);
		passenger = Passenger.builder().name("Uxia").build();
		passengerSet.add(passenger);

		Ship ship = Ship.builder()
				.crew(crewSet)
				.passengers(passengerSet)
				.name("EARTH-2100-JVL")
				.build();

		Mission mission1 = Mission.builder().name("From earth to mars").initDate(LocalDateTime.now().minusYears(1))
				.endDate(LocalDateTime.now().plusYears(3)).ship(ship).planets(planetSet).build();
		List<Mission> missions = new ArrayList<>();
		missions.add(mission1);
		return missions;
	}
}
