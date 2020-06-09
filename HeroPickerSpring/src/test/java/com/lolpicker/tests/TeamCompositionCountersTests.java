package com.lolpicker.tests;

import static com.lolpicker.model.Champion.Aatrox;
import static com.lolpicker.model.Champion.Annie;
import static com.lolpicker.model.Champion.Ashe;
import static com.lolpicker.model.Champion.Azir;
import static com.lolpicker.model.Champion.ChoGath;
import static com.lolpicker.model.Champion.DrMundo;
import static com.lolpicker.model.Champion.Galio;
import static com.lolpicker.model.Champion.Heimerdinger;
import static com.lolpicker.model.Champion.Janna;
import static com.lolpicker.model.Champion.Jayce;
import static com.lolpicker.model.Champion.Jinx;
import static com.lolpicker.model.Champion.Katarina;
import static com.lolpicker.model.Champion.Kled;
import static com.lolpicker.model.Champion.KogMaw;
import static com.lolpicker.model.Champion.Lissandra;
import static com.lolpicker.model.Champion.Lulu;
import static com.lolpicker.model.Champion.Malphite;
import static com.lolpicker.model.Champion.Maokai;
import static com.lolpicker.model.Champion.Nautilus;
import static com.lolpicker.model.Champion.Nidalee;
import static com.lolpicker.model.Champion.Orianna;
import static com.lolpicker.model.Champion.Ornn;
import static com.lolpicker.model.Champion.Poppy;
import static com.lolpicker.model.Champion.Rammus;
import static com.lolpicker.model.Champion.Sejuani;
import static com.lolpicker.model.Champion.Senna;
import static com.lolpicker.model.Champion.Soraka;
import static com.lolpicker.model.Champion.Syndra;
import static com.lolpicker.model.Champion.Varus;
import static com.lolpicker.model.Champion.Vayne;
import static com.lolpicker.model.Champion.Vladimir;
import static com.lolpicker.model.Champion.Yasuo;
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

}
