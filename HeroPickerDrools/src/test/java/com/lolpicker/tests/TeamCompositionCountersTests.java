package com.lolpicker.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.ChampionPick;
import static com.lolpicker.model.Champion.*;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Position;

public class TeamCompositionCountersTests {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
        KieServices ks = KieServices.Factory.get();
        kieContainer = ks.getKieClasspathContainer();
	}
	@Test
	public void adheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
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
		assertEquals(20, championSelect.getCompositionCounters().size());
		assertTrue(championSelect.getCompositionCounters().contains(Rammus));
		assertTrue(championSelect.getCompositionCounters().contains(Malphite));
		assertTrue(championSelect.getCompositionCounters().contains(Ornn));
		assertTrue(championSelect.getCompositionCounters().contains(Poppy));
		assertTrue(championSelect.getCompositionCounters().contains(Sejuani));
	}

	@Test
	public void apheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
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
		assertEquals(19, championSelect.getCompositionCounters().size());
		assertTrue(championSelect.getCompositionCounters().contains(Galio));
		assertTrue(championSelect.getCompositionCounters().contains(ChoGath));
		assertTrue(championSelect.getCompositionCounters().contains(DrMundo));
		assertTrue(championSelect.getCompositionCounters().contains(Maokai));
		assertTrue(championSelect.getCompositionCounters().contains(Ornn));
	}

	@Test
	public void tankheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick malphite = new ChampionPick(Malphite, false, false);
		ChampionPick ornn = new ChampionPick(Ornn, false, false);
		ChampionPick nautilus = new ChampionPick(Nautilus, false, false);
		kSession.insert(malphite);
		kSession.insert(ornn);
		kSession.insert(nautilus);
		kSession.fireAllRules();
		assertEquals(15, championSelect.getCompositionCounters().size());
		assertTrue(championSelect.getCompositionCounters().contains(Vayne));
		assertTrue(championSelect.getCompositionCounters().contains(KogMaw));
		assertTrue(championSelect.getCompositionCounters().contains(Jinx));
		assertTrue(championSelect.getCompositionCounters().contains(Ashe));
		assertTrue(championSelect.getCompositionCounters().contains(Azir));
	}

	@Test
	public void healerheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick soraka = new ChampionPick(Soraka, false, false);
		ChampionPick senna = new ChampionPick(Senna, false, false);
		ChampionPick vladimir = new ChampionPick(Vladimir, false, false);
		kSession.insert(soraka);
		kSession.insert(senna);
		kSession.insert(vladimir);
		kSession.fireAllRules();
		assertEquals(15, championSelect.getCompositionCounters().size());
		assertTrue(championSelect.getCompositionCounters().contains(Kled));
		assertTrue(championSelect.getCompositionCounters().contains(Katarina));
		assertTrue(championSelect.getCompositionCounters().contains(Varus));
		assertTrue(championSelect.getCompositionCounters().contains(Syndra));
		assertTrue(championSelect.getCompositionCounters().contains(Orianna));
	}

	@Test
	public void pokeheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick jayce = new ChampionPick(Jayce, false, false);
		ChampionPick nidalee = new ChampionPick(Nidalee, false, false);
		ChampionPick varus = new ChampionPick(Varus, false, false);
		kSession.insert(jayce);
		kSession.insert(nidalee);
		kSession.insert(varus);
		kSession.fireAllRules();
		assertEquals(17, championSelect.getCompositionCounters().size());
		assertTrue(championSelect.getCompositionCounters().contains(Soraka));
		assertTrue(championSelect.getCompositionCounters().contains(Janna));
		assertTrue(championSelect.getCompositionCounters().contains(Lulu));
		assertTrue(championSelect.getCompositionCounters().contains(Nidalee));
		assertTrue(championSelect.getCompositionCounters().contains(Vladimir));
	}

}
