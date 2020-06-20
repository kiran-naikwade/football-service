package com.sapient.football.web;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.football.entity.Team;
import com.sapient.football.service.FootballTeamService;

@SpringBootTest
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
public class FootballTeamsRestControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@SpyBean
	private FootballTeamService teamService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void saveTeamTest() throws Exception {
		//Team(String name, String city, int noOfPlayer, String country, String league, int position, Date date)
		Team team = new Team("Team1", "Pune", 20, "India", "League1", 3, new Date());
		String teamStr = new ObjectMapper().writeValueAsString(team);

		mockMvc.perform(post("/api/teams").content(teamStr).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated());

		verify(teamService).save(Mockito.any(Team.class));
	}


	@Test
	public void getByTeamNameTest() throws Exception {
		mockMvc.perform(get("/api/teams/name/Team1")).andExpect(status().isOk());
	}
	
	@Test
	public void getByLeagueNameTest() throws Exception {
		mockMvc.perform(get("/api/teams/league/League1")).andExpect(status().isOk());
	}
	
	@Test
	public void getByCountryNameTest() throws Exception {
		mockMvc.perform(get("/api/teams/country/India")).andExpect(status().isOk());
	}

	@Test
	public void getAllTeamsTest() throws Exception {
		mockMvc.perform(get("/api/teams")).andExpect(status().isOk());
	}

}
