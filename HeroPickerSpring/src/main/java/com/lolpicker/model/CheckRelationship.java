package com.lolpicker.model;

public class CheckRelationship {
	private String champion1;
	private String champion2;
	private String relationship;
	public CheckRelationship() {
		super();
		this.relationship = "None";
	}
	public CheckRelationship(String champion1, String champion2) {
		super();
		this.champion1 = champion1;
		this.champion2 = champion2;
		this.relationship = "None";
	}
	public String getChampion1() {
		return champion1;
	}
	public void setChampion1(String champion1) {
		this.champion1 = champion1;
	}
	public String getChampion2() {
		return champion2;
	}
	public void setChampion2(String champion2) {
		this.champion2 = champion2;
	}
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
}
