package com.lolpicker.dto;

import java.util.ArrayList;

public class CompositionRuleDto {
	 private int composition;
	 private int adChampions;
	 private int apChampions;
	 private int tankChampions;
	 private int healerChampions;
	 private int pokeChampions;
	 ArrayList<String> counters = new ArrayList<String>();

	 public CompositionRuleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public CompositionRuleDto(int composition, int adChampions, int apChampions, int tankChampions,
			int healerChampions, int pokeChampions, ArrayList<String> counters) {
		super();
		this.composition = composition;
		this.adChampions = adChampions;
		this.apChampions = apChampions;
		this.tankChampions = tankChampions;
		this.healerChampions = healerChampions;
		this.pokeChampions = pokeChampions;
		this.counters = counters;
	}

	public int getComposition() {
	  return composition;
	 }

	 public int getAdChampions() {
	  return adChampions;
	 }

	 public int getApChampions() {
	  return apChampions;
	 }

	 public int getTankChampions() {
	  return tankChampions;
	 }

	 public int getHealerChampions() {
	  return healerChampions;
	 }

	 public int getPokeChampions() {
	  return pokeChampions;
	 }

	 public void setComposition(int composition) {
	  this.composition = composition;
	 }

	 public void setAdChampions(int adChampions) {
	  this.adChampions = adChampions;
	 }

	 public void setApChampions(int apChampions) {
	  this.apChampions = apChampions;
	 }

	 public void setTankChampions(int tankChampions) {
	  this.tankChampions = tankChampions;
	 }

	 public void setHealerChampions(int healerChampions) {
	  this.healerChampions = healerChampions;
	 }

	 public void setPokeChampions(int pokeChampions) {
	  this.pokeChampions = pokeChampions;
	 }

	public ArrayList<String> getCounters() {
		return counters;
	}

	public void setCounters(ArrayList<String> counters) {
		this.counters = counters;
	}
	 
	 
	}