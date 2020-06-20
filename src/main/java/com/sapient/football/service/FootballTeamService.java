package com.sapient.football.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.football.entity.Team;
import com.sapient.football.repository.FootballTeamRepository;

@Service
public class FootballTeamService {

	private static final Logger logger = LoggerFactory.getLogger(FootballTeamService.class);

	private FootballTeamRepository teamRepository;

	@Autowired
	public FootballTeamService(FootballTeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	public List<Team> findAll() {
		return teamRepository.findAll();
	}

	public void save(Team team) {
		logger.info("saving team: {}", team);
		teamRepository.saveAndFlush(team);
	}

	public Team getByTeamName(String name) {
		logger.info("finding team with name: {}", name);
		return teamRepository.findByName(name);
	}

	public Team getByLeagueName(String name) {
		logger.info("finding team with name: {}", name);
		return teamRepository.findByLeague(name);
	}

	public Team getByCountryName(String name) {
		logger.info("finding team with name: {}", name);
		return teamRepository.findByCountry(name);
	}

}
