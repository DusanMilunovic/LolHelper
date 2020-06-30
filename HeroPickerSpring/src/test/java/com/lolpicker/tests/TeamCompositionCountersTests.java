package com.lolpicker.tests;

import static com.lolpicker.model.Champion.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.Champion;
import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Position;

public class TeamCompositionCountersTests {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
		KieServices ks = KieServices.Factory.get();
		kieContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroPickerKjar", "0.0.1-SNAPSHOT"));
	}
	@Test
	public void adheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick aatrox = new ChampionPick(Aatrox, false, false);
		ChampionPick senna = new ChampionPick(Senna, false, false);
		ChampionPick yasuo = new ChampionPick(Yasuo, false, false);
		ChampionPick vayne = new ChampionPick(Vayne, false, false);
		kSession.insert(aatrox);
		kSession.insert(yasuo);
		kSession.insert(senna);
		kSession.insert(vayne);
		kSession.fireAllRules();
		assertTrue(championSelect.getCompositionCounters().containsKey("adheavy"));
		ArrayList<Champion> champions = championSelect.getCompositionCounters().get("adheavy"); 
		assertEquals(20, champions.size());
		assertTrue(champions.contains(Rammus));
		assertTrue(champions.contains(Malphite));
		assertTrue(champions.contains(Ornn));
		assertTrue(champions.contains(Poppy));
		assertTrue(champions.contains(Sejuani));
	}

	@Test
	public void apheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick syndra = new ChampionPick(Syndra, false, false);
		ChampionPick annie = new ChampionPick(Annie, false, false);
		ChampionPick lissandra = new ChampionPick(Lissandra, false, false);
		ChampionPick heimerdinger = new ChampionPick(Heimerdinger, false, false);
		kSession.insert(syndra);
		kSession.insert(annie);
		kSession.insert(lissandra);
		kSession.insert(heimerdinger);
		kSession.fireAllRules();
		assertTrue(championSelect.getCompositionCounters().containsKey("apheavy"));
		ArrayList<Champion> champions = championSelect.getCompositionCounters().get("apheavy"); 
		assertEquals(19, champions.size());
		assertTrue(champions.contains(Galio));
		assertTrue(champions.contains(ChoGath));
		assertTrue(champions.contains(DrMundo));
		assertTrue(champions.contains(Maokai));
		assertTrue(champions.contains(Ornn));
	}

	@Test
	public void tankheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick malphite = new ChampionPick(Malphite, false, false);
		ChampionPick ornn = new ChampionPick(Ornn, false, false);
		ChampionPick nautilus = new ChampionPick(Nautilus, false, false);
		kSession.insert(malphite);
		kSession.insert(ornn);
		kSession.insert(nautilus);
		kSession.fireAllRules();
		assertTrue(championSelect.getCompositionCounters().containsKey("tankheavy"));
		ArrayList<Champion> champions = championSelect.getCompositionCounters().get("tankheavy"); 
		assertEquals(15, champions.size());
		assertTrue(champions.contains(Vayne));
		assertTrue(champions.contains(KogMaw));
		assertTrue(champions.contains(Jinx));
		assertTrue(champions.contains(Ashe));
		assertTrue(champions.contains(Azir));
	}

	@Test
	public void healerheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick soraka = new ChampionPick(Soraka, false, false);
		ChampionPick senna = new ChampionPick(Senna, false, false);
		ChampionPick vladimir = new ChampionPick(Vladimir, false, false);
		kSession.insert(soraka);
		kSession.insert(senna);
		kSession.insert(vladimir);
		kSession.fireAllRules();
		assertTrue(championSelect.getCompositionCounters().containsKey("healerheavy"));
		ArrayList<Champion> champions = championSelect.getCompositionCounters().get("healerheavy"); 
		assertEquals(15, champions.size());
		assertTrue(champions.contains(Kled));
		assertTrue(champions.contains(Katarina));
		assertTrue(champions.contains(Varus));
		assertTrue(champions.contains(Syndra));
		assertTrue(champions.contains(Orianna));
	}

	@Test
	public void pokeheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick jayce = new ChampionPick(Jayce, false, false);
		ChampionPick nidalee = new ChampionPick(Nidalee, false, false);
		ChampionPick varus = new ChampionPick(Varus, false, false);
		kSession.insert(jayce);
		kSession.insert(nidalee);
		kSession.insert(varus);
		kSession.fireAllRules();
		assertTrue(championSelect.getCompositionCounters().containsKey("pokeheavy"));
		ArrayList<Champion> champions = championSelect.getCompositionCounters().get("pokeheavy"); 
		assertEquals(17, champions.size());
		assertTrue(champions.contains(Soraka));
		assertTrue(champions.contains(Janna));
		assertTrue(champions.contains(Lulu));
		assertTrue(champions.contains(Nidalee));
		assertTrue(champions.contains(Vladimir));
	}

	@Test
	public void funnelComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick jayce = new ChampionPick(Jayce, false, false);
		ChampionPick nami = new ChampionPick(Nami, false, false);
		ChampionPick soraka = new ChampionPick(Soraka, false, false);
		kSession.insert(jayce);
		kSession.insert(nami);
		kSession.insert(soraka);
		kSession.fireAllRules();
		assertTrue(championSelect.getCompositionCounters().containsKey("funnel"));
		ArrayList<Champion> champions = championSelect.getCompositionCounters().get("funnel"); 
		assertEquals(12, champions.size());
		assertTrue(champions.contains(Olaf));
		assertTrue(champions.contains(Kled));
		assertTrue(champions.contains(Wukong));
		assertTrue(champions.contains(LeeSin));
		assertTrue(champions.contains(Irelia));
	}

}
