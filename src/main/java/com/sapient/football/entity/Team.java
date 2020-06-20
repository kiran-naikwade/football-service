package com.sapient.football.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@SuppressWarnings("serial")
public class Team implements Serializable {

	@Id
	private String name;

	private String city;

	@Column(unique=true)
	private String league;

	private int noOfPlayer;

	@Column(unique=true)
	private String country;

	private int position;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date date;

	public Team(String name, String city, int noOfPlayer, String country, String league, int position, Date date) {
		this.name = name;
		this.city = city;
		this.noOfPlayer = noOfPlayer;
		this.country = country;
		this.league = league;
		this.position = position;
		this.date = date;
	}

	public Team() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public int getNoOfPlayer() {
		return noOfPlayer;
	}

	public void setNoOfPlayer(int noOfPlayer) {
		this.noOfPlayer = noOfPlayer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
