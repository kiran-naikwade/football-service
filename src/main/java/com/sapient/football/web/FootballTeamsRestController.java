package com.sapient.football.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.football.entity.Team;
import com.sapient.football.service.FootballTeamService;

@RestController
@RequestMapping("/api")
public class FootballTeamsRestController {

	@Autowired
	private FootballTeamService teamService;

	@RequestMapping(value = "/teams", method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Team team) {
		team.setDate(new Date());
		teamService.save(team);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/teams/name/{name}", method = RequestMethod.GET)
	public ResponseEntity<Team> getByTeamName(@PathVariable("name") String name) {
		return new ResponseEntity<>(teamService.getByTeamName(name), HttpStatus.OK);
	}

	@RequestMapping(value = "/teams/league/{league}", method = RequestMethod.GET)
	public ResponseEntity<Team> getByLeagueName(@PathVariable("league") String league) {
		return new ResponseEntity<>(teamService.getByLeagueName(league), HttpStatus.OK);
	}

	@RequestMapping(value = "/teams/country/{country}", method = RequestMethod.GET)
	public ResponseEntity<Team> getByCountryName(@PathVariable("country") String country) {
		return new ResponseEntity<>(teamService.getByCountryName(country), HttpStatus.OK);
	}

	@RequestMapping(value = "/teams", method = RequestMethod.GET)
	public ResponseEntity<List<Team>> teams(@RequestParam(required = false) String sort,
			@RequestParam(required = false) String direction) {
		return new ResponseEntity<>(teamService.findAll(), HttpStatus.OK);
	}

}
