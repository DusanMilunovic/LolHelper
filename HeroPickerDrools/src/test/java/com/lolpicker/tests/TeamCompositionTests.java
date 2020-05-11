package com.lolpicker.tests;

import static com.lolpicker.model.Champion.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.CompositionType;
import com.lolpicker.model.Position;

public class TeamCompositionTests {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
        KieServices ks = KieServices.Factory.get();
        kieContainer = ks.getKieClasspathContainer();
	}
	
	@Test
	public void enemyTeamAdheavyComp() {
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
		assertTrue(championSelect.getEnemyTeamComposition().contains(CompositionType.adheavy));
		assertEquals(1, championSelect.getEnemyTeamComposition().size());
	}

	@Test
	public void myTeamAdheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick aatrox = new ChampionPick(Aatrox, true, false);
		ChampionPick senna = new ChampionPick(Senna, true, false);
		ChampionPick yasuo = new ChampionPick(Yasuo, true, false);
		kSession.insert(aatrox);
		kSession.insert(yasuo);
		kSession.insert(senna);
		kSession.fireAllRules();
		assertTrue(championSelect.getMyTeamComposition().contains(CompositionType.adheavy));
		assertEquals(1, championSelect.getMyTeamComposition().size());
	}

	@Test
	public void enemyTeamApheavyComp() {
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
		assertTrue(championSelect.getEnemyTeamComposition().contains(CompositionType.apheavy));
		assertEquals(1, championSelect.getEnemyTeamComposition().size());
	}

	@Test
	public void myTeamApheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick syndra = new ChampionPick(Syndra, true, false);
		ChampionPick annie = new ChampionPick(Annie, true, false);
		ChampionPick lissandra = new ChampionPick(Lissandra, true, false);
		kSession.insert(syndra);
		kSession.insert(annie);
		kSession.insert(lissandra);
		kSession.fireAllRules();
		assertTrue(championSelect.getMyTeamComposition().contains(CompositionType.apheavy));
		assertEquals(1, championSelect.getMyTeamComposition().size());
	}

	@Test
	public void enemyTeamTankheavyComp() {
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
		assertTrue(championSelect.getEnemyTeamComposition().contains(CompositionType.tankheavy));
		assertEquals(1, championSelect.getEnemyTeamComposition().size());
	}

	@Test
	public void myTeamTankheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick malphite = new ChampionPick(Malphite, true, false);
		ChampionPick ornn = new ChampionPick(Ornn, true, false);
		kSession.insert(malphite);
		kSession.insert(ornn);
		kSession.fireAllRules();
		assertTrue(championSelect.getMyTeamComposition().contains(CompositionType.tankheavy));
		assertEquals(1, championSelect.getMyTeamComposition().size());
	}

	@Test
	public void enemyTeamHealerheavyComp() {
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
		assertTrue(championSelect.getEnemyTeamComposition().contains(CompositionType.healerheavy));
		assertEquals(1, championSelect.getEnemyTeamComposition().size());
	}

	@Test
	public void myTeamHealerheavyComp() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick soraka = new ChampionPick(Soraka, true, false);
		ChampionPick senna = new ChampionPick(Senna, true, false);
		kSession.insert(soraka);
		kSession.insert(senna);
		kSession.fireAllRules();
		assertTrue(championSelect.getMyTeamComposition().contains(CompositionType.healerheavy));
		assertEquals(1, championSelect.getMyTeamComposition().size());
	}

}
