package com.lolpicker.main;

import static com.lolpicker.model.Champion.*;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Position;

public class Main {

	public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
    	ChampionSelect cs = new ChampionSelect(Position.top);
    	ChampionPick cp1 = new ChampionPick(Aatrox, false, false);
    	ChampionPick cp2 = new ChampionPick(Olaf, false, false);
    	ChampionPick cp3 = new ChampionPick(Zed, false, false);
    	ChampionPick cp4 = new ChampionPick(Vladimir, false, false);
    	ChampionPick cp5 = new ChampionPick(Soraka, false, false);
    	ChampionPick cp6 = new ChampionPick(Taliyah, true, false);
    	ChampionPick cp7 = new ChampionPick(Orianna, true, false);
    	ChampionPick cp8 = new ChampionPick(Aphelios, true, false);
    	ChampionPick cp9 = new ChampionPick(Zilean, true, false);
    	ChampionPick cp10 = new ChampionPick(Fiora, true, true);
    	kSession.insert(cs);
    	kSession.insert(cp1);
    	kSession.insert(cp2);
    	kSession.insert(cp3);
    	kSession.insert(cp4);
    	kSession.insert(cp5);
    	kSession.insert(cp6);
    	kSession.insert(cp7);
    	kSession.insert(cp8);
    	kSession.insert(cp9);
    	kSession.fireAllRules();
    	System.out.println(cs);
		kSession.insert(cp10);
		kSession.fireAllRules();
    	kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
    	System.out.println(cs);
	}

}
