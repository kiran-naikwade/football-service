package com.sapient.football.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.football.exception.BadRequestException;
import com.sapient.football.model.Country;
import com.sapient.football.model.Leagues;
import com.sapient.football.model.TeamStanding;
import com.sapient.football.model.TeamStandingRequest;

@Service
public class TeamStandingService {

	private final TeamStandingRestClient teamStandingRestClient;

	@Autowired
	public TeamStandingService(TeamStandingRestClient teamStandingRestClient) {
		this.teamStandingRestClient = teamStandingRestClient;
	}

	/**
	 * 
	 * @param teamStandingRequest
	 * @return
	 */
	public TeamStanding getTeamStanding(TeamStandingRequest teamStandingRequest) {
		TeamStanding teamStandingsFiltered = null;
		// TODO: Get countries using
		// https://apiv2.apifootball.com/?action=get_countries&APIkey=xxxxxxxxxxxxxx
		List<Country> countries = getCountries();
		Country country = getCountryByName(teamStandingRequest.getCountryName(), countries);
		if (country == null) {
			System.out.println("Country Not Found by name " + teamStandingRequest.getCountryName());
			return teamStandingsFiltered;
		}

		// TODO: Get Competitions using
		// https://apiv2.apifootball.com/?action=get_leagues&country_id=41&APIkey=xxxxxxxxxxxxxx
		List<Leagues> leaguesList = getLeagues(country.getId());
		Leagues leagues = getLeaguesByName(teamStandingRequest.getLeagueName(), leaguesList);

		if (leagues == null) {
			System.out.println("Leagues Not Found by name " + teamStandingRequest.getCountryName());
			return teamStandingsFiltered;
		}
		// TODO:Get standings using
		// https://apiv2.apifootball.com/?action=get_standings&league_id=148&APIkey=xxxxxxxxxxxxxx

		List<TeamStanding> teamStandings = getTeamStanding(leagues.getLeagueId());

		teamStandingsFiltered = getFilteredTeamStanding(teamStandingRequest.getTeamName(), teamStandings);
		if (teamStandingsFiltered == null) {
			System.out.println("Team Not Found by name " + teamStandingRequest.getCountryName());
			return teamStandingsFiltered;
		}
		//Return response
		teamStandingsFiltered.setCountryId(country.getId());
		//return TeamStandingDto.from(teamStandingsFiltered);
		return teamStandingsFiltered;
	}

	/**
	 * 
	 * @param countryName
	 * @param countries
	 * @return
	 */
	private Country getCountryByName(String countryName, List<Country> countries) {
		return countries.stream().filter(c -> countryName.equalsIgnoreCase(c.getName())).findFirst().orElse(null);
	}

	/**
	 * 
	 * @param leagueName
	 * @param leaguesList
	 * @return
	 */
	private Leagues getLeaguesByName(String leagueName, List<Leagues> leaguesList) {
		return leaguesList.stream().filter(l -> leagueName.equalsIgnoreCase(l.getLeagueName())).findFirst()
				.orElse(null);
	}

	/**
	 * 
	 * @param teamName
	 * @param teamStandings
	 * @return
	 */
	private TeamStanding getFilteredTeamStanding(String teamName, List<TeamStanding> teamStandings) {
		return teamStandings.stream().filter(t -> teamName.equalsIgnoreCase(t.getTeamName())).findFirst().orElse(null);
	}

	/**
	 * 
	 * @return
	 */
	private List<Country> getCountries() {
		return new ArrayList<>(Arrays.asList(teamStandingRestClient.getCountries()));
	}

	/**
	 * 
	 * @param countryId
	 * @return
	 */
	private List<Leagues> getLeagues(int countryId) {
		return new ArrayList<>(Arrays.asList(teamStandingRestClient.getLeagues(countryId)));
	}

	/**
	 * 
	 * @param leagueId
	 * @return
	 */
	private List<TeamStanding> getTeamStanding(int leagueId) {
		return new ArrayList<>(Arrays.asList(teamStandingRestClient.getTeamStanding(leagueId)));
	}

}
