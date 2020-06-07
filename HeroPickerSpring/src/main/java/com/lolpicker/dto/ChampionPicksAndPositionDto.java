package com.lolpicker.dto;

import java.util.ArrayList;

public class ChampionPicksAndPositionDto {

	private ArrayList<ChampionPickDto> picks;
	private String position;
	
	public ChampionPicksAndPositionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChampionPicksAndPositionDto(ArrayList<ChampionPickDto> picks, String position) {
		super();
		this.picks = picks;
		this.position = position;
	}

	public ArrayList<ChampionPickDto> getPicks() {
		return picks;
	}
	public void setPicks(ArrayList<ChampionPickDto> picks) {
		this.picks = picks;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
}
