package com.lolpicker.dto;

import java.util.ArrayList;

import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.ChampionSelect;

public class ChampionSelectAndPicksDto {
	private ChampionSelect championSelect;
	private ArrayList<ChampionPick> picks;
	public ChampionSelectAndPicksDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ChampionSelectAndPicksDto(ChampionSelect championSelect, ArrayList<ChampionPick> picks) {
		super();
		this.championSelect = championSelect;
		this.picks = picks;
	}
	public ChampionSelect getChampionSelect() {
		return championSelect;
	}
	public void setChampionSelect(ChampionSelect championSelect) {
		this.championSelect = championSelect;
	}
	public ArrayList<ChampionPick> getPicks() {
		return picks;
	}
	public void setPicks(ArrayList<ChampionPick> picks) {
		this.picks = picks;
	}
	
}
