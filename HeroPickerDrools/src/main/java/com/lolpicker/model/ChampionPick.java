package com.lolpicker.model;

import java.util.ArrayList;

public class ChampionPick {
	private Champion champion;
	private ArrayList<Champion> counterPicks = new ArrayList<>();
	private ArrayList<Clasz> classes = new ArrayList<>();
	private Position position = Position.undefined;
	private StrongPoint strongPoint = null;
	private boolean myPick;
	private boolean myTeam;

	public ChampionPick() {
		super();
	}

	public ChampionPick(Champion champion, boolean myTeam, boolean myPick) {
		super();
		this.champion = champion;
		this.myTeam = myTeam;
		this.myPick = myPick;
	}

	public Champion getChampion() {
		return champion;
	}

	public void setChampion(Champion champion) {
		this.champion = champion;
	}

	public ArrayList<Champion> getCounterPicks() {
		return counterPicks;
	}

	public void setCounterPicks(ArrayList<Champion> counterPicks) {
		this.counterPicks = counterPicks;
	}

	public ArrayList<Clasz> getClasses() {
		return classes;
	}

	public void setClasses(ArrayList<Clasz> classes) {
		this.classes = classes;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public StrongPoint getStrongPoint() {
		return strongPoint;
	}

	public void setStrongPoint(StrongPoint strongPoint) {
		this.strongPoint = strongPoint;
	}

	public boolean isMyPick() {
		return myPick;
	}

	public void setMyPick(boolean myPick) {
		this.myPick = myPick;
	}

	public boolean isMyTeam() {
		return myTeam;
	}

	public void setMyTeam(boolean myTeam) {
		this.myTeam = myTeam;
	}
	
}
