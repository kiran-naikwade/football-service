package com.sapient.football.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.football.model.TeamStanding;
import com.sapient.football.model.TeamStandingRequest;
import com.sapient.football.service.TeamStandingService;

@RestController
@RequestMapping("/service/standing")
public class FootballStandingController {

	private final TeamStandingService teamStandingService;

	@Autowired
	public FootballStandingController(TeamStandingService teamStandingService) {
		this.teamStandingService = teamStandingService;
	}

	@GetMapping
	public ResponseEntity<TeamStanding> getStandings(@RequestParam(value = "teamName") String teamName,
		      @RequestParam(value = "countryName") String countryName,
		      @RequestParam(value = "leagueName") String leagueName) {
		//TODO: Validate request
		System.out.println("teamName :" + teamName);
		System.out.println("countryName :" + countryName);
		System.out.println("leagueName :" + leagueName);
		TeamStandingRequest request = new TeamStandingRequest();
		request.setCountryName(countryName);
		request.setLeagueName(leagueName);
		request.setTeamName(teamName);
				
		return ResponseEntity.ok(teamStandingService.getTeamStanding(request));
	}

}

