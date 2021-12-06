package com.example.misiones.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.misiones.DataForTest;
import com.example.misiones.config.JacksonConfig;
import com.example.misiones.dto.request.MissionRequest;
import com.example.misiones.model.Mission;
import com.example.misiones.service.MissionService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Import(JacksonConfig.class)
class MissionControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private MissionService missionService;

	@Test
	void testGetAllMissions() throws Exception {
		// given
		List<Mission> missions = new ArrayList<>();
		missions.add(DataForTest.simpleMission("Mission one", LocalDateTime.now().plusDays(1)));
		missions.add(DataForTest.simpleMission("Mission two", LocalDateTime.now().plusDays(1).plusYears(3)));
		when(missionService.findAll()).thenReturn(missions);
		// action
		final String uri = "/api/v1/missions";
		this.mockMvc.perform(get(uri)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is(missions.get(0).getName())))
				.andExpect(jsonPath("$[1].name", is(missions.get(1).getName()))).andDo(MockMvcResultHandlers.print());
		
		verify(missionService, times(1)).findAll();
	}

	@Test
	void testCreateNewMission() throws Exception {
		MissionRequest req = DataForTest.missionRequest("First test", LocalDateTime.now().plusDays(1));
		Mission mission = new Mission();
		mission.setId(1L);
		mission.setInitDate(req.getInitDate());
		when(missionService.save(req)).thenReturn(mission);
		//
		final String uri = "/api/v1/missions/save";
		this.mockMvc
			.perform(post(uri)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
			.content(mapper.writeValueAsString(req)))
			.andExpect(jsonPath("$.id").value(Matchers.is(mission.getId().intValue())))
			.andExpect(jsonPath("$.initDate").value(Matchers.is(mission.getInitDate().format(DateTimeFormatter.ofPattern(JacksonConfig.DATE_WITH_HOUR_FORMAT)))))
			.andExpect(MockMvcResultMatchers.status().is(HttpStatus.CREATED.value()))
			.andDo(MockMvcResultHandlers.print());
		
		verify(missionService, times(1)).save(req);
	}
}
