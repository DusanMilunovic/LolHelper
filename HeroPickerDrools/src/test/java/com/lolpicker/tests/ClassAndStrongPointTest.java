package com.lolpicker.tests;

import static com.lolpicker.model.Champion.Aatrox;
import static com.lolpicker.model.Champion.Cassiopeia;
import static com.lolpicker.model.Champion.Corki;
import static com.lolpicker.model.Champion.Malzahar;
import static com.lolpicker.model.Champion.Senna;
import static com.lolpicker.model.Champion.Soraka;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Clasz;
import com.lolpicker.model.StrongPoint;

public class ClassAndStrongPointTest {

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
		ChampionPick corki = new ChampionPick(Corki, false, false);
		ChampionPick cassiopeia = new ChampionPick(Cassiopeia, false, false);
		ChampionPick malzahar = new ChampionPick(Malzahar, false, false);
		ChampionPick senna = new ChampionPick(Senna, false, false);
		ChampionPick soraka = new ChampionPick(Soraka, false, false);
		kSession.insert(aatrox);
		kSession.insert(corki);
		kSession.insert(cassiopeia);
		kSession.insert(malzahar);
		kSession.insert(senna);
		kSession.insert(soraka);
		kSession.fireAllRules();
		assertTrue(aatrox.getClasses().contains(Clasz.adcarry));
		assertEquals(StrongPoint.balanced, aatrox.getStrongPoint());
		assertTrue(corki.getClasses().contains(Clasz.apcarry));
		assertTrue(corki.getClasses().contains(Clasz.poke));
		assertEquals(StrongPoint.late, corki.getStrongPoint());
		assertTrue(cassiopeia.getClasses().contains(Clasz.apcarry));
		assertEquals(StrongPoint.late, cassiopeia.getStrongPoint());
		assertTrue(malzahar.getClasses().contains(Clasz.apcarry));
		assertEquals(StrongPoint.late, malzahar.getStrongPoint());
		assertTrue(senna.getClasses().contains(Clasz.adcarry));
		assertTrue(senna.getClasses().contains(Clasz.healer));
		assertEquals(StrongPoint.balanced, senna.getStrongPoint());
		assertTrue(soraka.getClasses().contains(Clasz.healer));
		assertEquals(StrongPoint.late, soraka.getStrongPoint());
	}


}
