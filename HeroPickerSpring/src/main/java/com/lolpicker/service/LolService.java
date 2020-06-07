package com.lolpicker.service;

import java.util.ArrayList;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lolpicker.dto.ChampionSelectAndPicksDto;
import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Position;

@Service
public class LolService {

	private final KieContainer kieContainer;

	@Autowired
	public LolService(KieContainer kieContainer) {
		this.kieContainer = kieContainer;
	}
	
	public ChampionSelectAndPicksDto getPicks(ArrayList<ChampionPick> championPicks, Position position) {
		KieSession kSession = kieContainer.newKieSession();
    	ChampionSelect cs = new ChampionSelect(position);
    	kSession.insert(cs);
    	for (ChampionPick cp: championPicks) {
    		kSession.insert(cp);
    	}
    	kSession.fireAllRules();
    	ChampionSelectAndPicksDto cscps = new ChampionSelectAndPicksDto();
    	cscps.setChampionSelect(cs);
    	cscps.setPicks(championPicks);
		return cscps;
	}

	public ChampionSelectAndPicksDto getAdvice(ArrayList<ChampionPick> championPicks, Position position) {
		KieSession kSession = kieContainer.newKieSession();
    	ChampionSelect cs = new ChampionSelect(position);
    	kSession.insert(cs);
    	for (ChampionPick cp: championPicks) {
    		kSession.insert(cp);
    	}
    	kSession.fireAllRules();
    	kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
    	ChampionSelectAndPicksDto cscps = new ChampionSelectAndPicksDto();
    	cscps.setChampionSelect(cs);
    	cscps.setPicks(championPicks);
		return cscps;
	}

}
