package com.lolpicker.dto;

public class ChampionPickDto {
	private String champion;
	private boolean myTeam;
	private boolean myChamp;

	public ChampionPickDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChampionPickDto(String champion, boolean myTeam, boolean myChamp) {
		super();
		this.champion = champion;
		this.myTeam = myTeam;
		this.myChamp = myChamp;
	}

	public String getChampion() {
		return champion;
	}

	public void setChampion(String champion) {
		this.champion = champion;
	}

	public boolean isMyTeam() {
		return myTeam;
	}

	public void setMyTeam(boolean myTeam) {
		this.myTeam = myTeam;
	}

	public boolean isMyChamp() {
		return myChamp;
	}

	public void setMyChamp(boolean myChamp) {
		this.myChamp = myChamp;
	}
	
}
