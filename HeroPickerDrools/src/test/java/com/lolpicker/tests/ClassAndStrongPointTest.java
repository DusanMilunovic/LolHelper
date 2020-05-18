package com.lolpicker.tests;

import static com.lolpicker.model.Champion.Aatrox;
import static com.lolpicker.model.Champion.Bard;
import static com.lolpicker.model.Champion.Blitzcrank;
import static com.lolpicker.model.Champion.Brand;
import static com.lolpicker.model.Champion.Cassiopeia;
import static com.lolpicker.model.Champion.Corki;
import static com.lolpicker.model.Champion.Darius;
import static com.lolpicker.model.Champion.Galio;
import static com.lolpicker.model.Champion.Heimerdinger;
import static com.lolpicker.model.Champion.Illaoi;
import static com.lolpicker.model.Champion.Irelia;
import static com.lolpicker.model.Champion.Kled;
import static com.lolpicker.model.Champion.Malzahar;
import static com.lolpicker.model.Champion.Maokai;
import static com.lolpicker.model.Champion.Neeko;
import static com.lolpicker.model.Champion.Pantheon;
import static com.lolpicker.model.Champion.Pyke;
import static com.lolpicker.model.Champion.Rumble;
import static com.lolpicker.model.Champion.Senna;
import static com.lolpicker.model.Champion.Soraka;
import static com.lolpicker.model.Champion.Swain;
import static com.lolpicker.model.Champion.Syndra;
import static com.lolpicker.model.Champion.Talon;
import static com.lolpicker.model.Champion.TwistedFate;
import static com.lolpicker.model.Champion.Wukong;
import static com.lolpicker.model.Champion.Xerath;
import static com.lolpicker.model.Champion.Ziggs;
import static com.lolpicker.model.Champion.Zyra;
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
import com.lolpicker.model.Position;
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
	public void classStrongPointsAndCountersTest() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		ChampionSelect championSelect = new ChampionSelect(Position.top);
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
		assertTrue(aatrox.getCounterPicks().contains(Pantheon));
		assertTrue(aatrox.getCounterPicks().contains(Kled));
		assertTrue(aatrox.getCounterPicks().contains(Wukong));
		assertTrue(aatrox.getCounterPicks().contains(Darius));
		assertTrue(aatrox.getCounterPicks().contains(Illaoi));
		assertTrue(corki.getClasses().contains(Clasz.apcarry));
		assertTrue(corki.getClasses().contains(Clasz.poke));
		assertEquals(StrongPoint.late, corki.getStrongPoint());
		assertTrue(corki.getCounterPicks().contains(TwistedFate));
		assertTrue(corki.getCounterPicks().contains(Talon));
		assertTrue(corki.getCounterPicks().contains(Irelia));
		assertTrue(corki.getCounterPicks().contains(Neeko));
		assertTrue(corki.getCounterPicks().contains(Rumble));
		assertTrue(cassiopeia.getClasses().contains(Clasz.apcarry));
		assertEquals(StrongPoint.late, cassiopeia.getStrongPoint());
		assertTrue(cassiopeia.getCounterPicks().contains(Heimerdinger));
		assertTrue(cassiopeia.getCounterPicks().contains(Pantheon));
		assertTrue(cassiopeia.getCounterPicks().contains(TwistedFate));
		assertTrue(cassiopeia.getCounterPicks().contains(Galio));
		assertTrue(cassiopeia.getCounterPicks().contains(Pyke));
		assertTrue(malzahar.getClasses().contains(Clasz.apcarry));
		assertEquals(StrongPoint.late, malzahar.getStrongPoint());
		assertTrue(malzahar.getCounterPicks().contains(Rumble));
		assertTrue(malzahar.getCounterPicks().contains(Talon));
		assertTrue(malzahar.getCounterPicks().contains(TwistedFate));
		assertTrue(malzahar.getCounterPicks().contains(Syndra));
		assertTrue(malzahar.getCounterPicks().contains(Ziggs));
		assertTrue(senna.getClasses().contains(Clasz.adcarry));
		assertTrue(senna.getClasses().contains(Clasz.healer));
		assertEquals(StrongPoint.balanced, senna.getStrongPoint());
		assertTrue(senna.getCounterPicks().contains(Maokai));
		assertTrue(senna.getCounterPicks().contains(Pyke));
		assertTrue(senna.getCounterPicks().contains(Zyra));
		assertTrue(senna.getCounterPicks().contains(Swain));
		assertTrue(senna.getCounterPicks().contains(Blitzcrank));
		assertTrue(soraka.getClasses().contains(Clasz.healer));
		assertEquals(StrongPoint.late, soraka.getStrongPoint());
		assertTrue(soraka.getCounterPicks().contains(Xerath));
		assertTrue(soraka.getCounterPicks().contains(Pyke));
		assertTrue(soraka.getCounterPicks().contains(Brand));
		assertTrue(soraka.getCounterPicks().contains(Zyra));
		assertTrue(soraka.getCounterPicks().contains(Bard));
	}


}
