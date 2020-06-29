package com.lolpicker.model;
import org.kie.api.definition.type.Position;


public class Friend {
	@Position(0) //to indicate position of each attribute, that allows to the engine identifie the params order to use on the query function
    private String champion1;
    @Position(1)
    private String champion2;
	public Friend() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Friend(String champion1, String champion2) {
		super();
		this.champion1 = champion1;
		this.champion2 = champion2;
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
}
