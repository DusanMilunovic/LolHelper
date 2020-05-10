package com.lolpicker.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.ChampionPick;
import static com.lolpicker.model.Champion.*;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Position;

public class PositionTests {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
        KieServices ks = KieServices.Factory.get();
        kieContainer = ks.getKieClasspathContainer();
	}
	
	@Test
	public void dijagnostika1() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect();
		kSession.insert(championSelect);
		ChampionPick aatrox = new ChampionPick(Aatrox, false, false);
		ChampionPick akali = new ChampionPick(Akali, false, false);
		kSession.insert(aatrox);
		kSession.insert(akali);
		kSession.fireAllRules();
		assertTrue(championSelect.isEnemyTopPicked());
		assertTrue(championSelect.isEnemyMidPicked());
		assertFalse(championSelect.isEnemyBotPicked());
		assertEquals(Position.top, aatrox.getPosition());
		assertEquals(Position.mid, akali.getPosition());
	}

}
