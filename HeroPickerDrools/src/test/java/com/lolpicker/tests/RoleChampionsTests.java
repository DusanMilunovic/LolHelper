package com.lolpicker.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Position;

public class RoleChampionsTests {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
        KieServices ks = KieServices.Factory.get();
        kieContainer = ks.getKieClasspathContainer();
	}
	
	@Test
	public void topChampions() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		kSession.fireAllRules();
		assertEquals(29, championSelect.getChampsForPosition().size());
	}

	@Test
	public void jungleChampions() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.jungle);
		kSession.insert(championSelect);
		kSession.fireAllRules();
		assertEquals(32, championSelect.getChampsForPosition().size());
	}

	@Test
	public void midChampions() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.mid);
		kSession.insert(championSelect);
		kSession.fireAllRules();
		assertEquals(31, championSelect.getChampsForPosition().size());
	}

	@Test
	public void botChampions() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.bot);
		kSession.insert(championSelect);
		kSession.fireAllRules();
		assertEquals(19, championSelect.getChampsForPosition().size());
	}

	@Test
	public void supportChampions() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.support);
		kSession.insert(championSelect);
		kSession.fireAllRules();
		assertEquals(26, championSelect.getChampsForPosition().size());
	}
	

}
