package com.sapient.football.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class FootballStandingsService {

	private RestTemplate restTemplate;
	
	@Autowired
	public FootballStandingsService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

//	@Value("${protecti.fuzzyhash.scan.url}")
//	private String fuzzyhashScanUrl;

	public void getFootballStandings(int id) throws RestClientException {
		
	}
	
	public Object getFootballStandings() throws RestClientException {
		try {
	
			final String baseUrl = "https://apiv2.apifootball.com/?action=get_standings&league_id=148&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
			URI uri = new URI(baseUrl);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			ResponseEntity<Object> result = restTemplate.getForEntity(uri, Object.class);			
			if (result.getStatusCode().value() == 200) {
				System.out.println("Success");							
				ArrayList<Object> response = (ArrayList<Object>) result.getBody();				
				for (Object object : response) {					
					System.out.println(object);					
				}											
			}
			return result;
		} catch (RestClientException | URISyntaxException e) {
			throw new RestClientException("Error while fetching standings");
		}

	}

}
