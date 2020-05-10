package com.lolpicker.model;

import java.util.ArrayList;

public class ChampionSelect {
	private ArrayList<Champion> champsForPosition = new ArrayList<>();
	private ArrayList<Champion> counterPicks = new ArrayList<>();
	private ArrayList<Champion> compositionCounters = new ArrayList<>();
	private ArrayList<CompositionType> enemyTeamComposition = new ArrayList<>();
	private ArrayList<CompositionType> myTeamComposition = new ArrayList<>();
	private boolean enemyTopPicked = false;
	private boolean enemyJunglePicked = false;
	private boolean enemyMidPicked = false;
	private boolean enemyBotPicked = false;
	private boolean enemySupportPicked = false;
	private boolean myTopPicked = false;
	private boolean myJunglePicked = false;
	private boolean myMidPicked = false;
	private boolean myBotPicked = false;
	private boolean mySupportPicked = false;

	public ChampionSelect() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Champion> getChampsForPosition() {
		return champsForPosition;
	}
	public void setChampsForPosition(ArrayList<Champion> champsForPosition) {
		this.champsForPosition = champsForPosition;
	}
	public ArrayList<Champion> getCounterPicks() {
		return counterPicks;
	}
	public void setCounterPicks(ArrayList<Champion> counterPicks) {
		this.counterPicks = counterPicks;
	}
	public ArrayList<Champion> getCompositionCounters() {
		return compositionCounters;
	}
	public void setCompositionCounters(ArrayList<Champion> compositionCounters) {
		this.compositionCounters = compositionCounters;
	}
	public ArrayList<CompositionType> getEnemyTeamComposition() {
		return enemyTeamComposition;
	}
	public void setEnemyTeamComposition(ArrayList<CompositionType> enemyTeamComposition) {
		this.enemyTeamComposition = enemyTeamComposition;
	}
	public ArrayList<CompositionType> getMyTeamComposition() {
		return myTeamComposition;
	}
	public void setMyTeamComposition(ArrayList<CompositionType> myTeamComposition) {
		this.myTeamComposition = myTeamComposition;
	}
	public boolean isEnemyTopPicked() {
		return enemyTopPicked;
	}
	public void setEnemyTopPicked(boolean enemyTopPicked) {
		this.enemyTopPicked = enemyTopPicked;
	}
	public boolean isEnemyJunglePicked() {
		return enemyJunglePicked;
	}
	public void setEnemyJunglePicked(boolean enemyJunglePicked) {
		this.enemyJunglePicked = enemyJunglePicked;
	}
	public boolean isEnemyMidPicked() {
		return enemyMidPicked;
	}
	public void setEnemyMidPicked(boolean enemyMidPicked) {
		this.enemyMidPicked = enemyMidPicked;
	}
	public boolean isEnemyBotPicked() {
		return enemyBotPicked;
	}
	public void setEnemyBotPicked(boolean enemyBotPicked) {
		this.enemyBotPicked = enemyBotPicked;
	}
	public boolean isEnemySupportPicked() {
		return enemySupportPicked;
	}
	public void setEnemySupportPicked(boolean enemySupportPicked) {
		this.enemySupportPicked = enemySupportPicked;
	}
	public boolean isMyTopPicked() {
		return myTopPicked;
	}
	public void setMyTopPicked(boolean myTopPicked) {
		this.myTopPicked = myTopPicked;
	}
	public boolean isMyJunglePicked() {
		return myJunglePicked;
	}
	public void setMyJunglePicked(boolean myJunglePicked) {
		this.myJunglePicked = myJunglePicked;
	}
	public boolean isMyMidPicked() {
		return myMidPicked;
	}
	public void setMyMidPicked(boolean myMidPicked) {
		this.myMidPicked = myMidPicked;
	}
	public boolean isMyBotPicked() {
		return myBotPicked;
	}
	public void setMyBotPicked(boolean myBotPicked) {
		this.myBotPicked = myBotPicked;
	}
	public boolean isMySupportPicked() {
		return mySupportPicked;
	}
	public void setMySupportPicked(boolean mySupportPicked) {
		this.mySupportPicked = mySupportPicked;
	}
	
}
