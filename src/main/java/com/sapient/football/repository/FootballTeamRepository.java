package com.sapient.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sapient.football.entity.Team;

public interface FootballTeamRepository extends JpaRepository<Team, String> {

	Team findByName(String name);

	Team findByLeague(String name);

	Team findByCountry(String name);
}
