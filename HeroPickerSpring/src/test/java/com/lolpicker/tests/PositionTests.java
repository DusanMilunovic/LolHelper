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
		kieContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroPickerKjar", "0.0.1-SNAPSHOT"));
	}
	
	@Test
	public void fncG2Game1() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick sylas = new ChampionPick(Sylas, true, false);
		ChampionPick leesin = new ChampionPick(LeeSin, true, false);
		ChampionPick veigar = new ChampionPick(Veigar, true, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, true, false);
		ChampionPick tahmkench = new ChampionPick(TahmKench, true, false);
		ChampionPick ornn = new ChampionPick(Ornn, false, false);
		ChampionPick jarvan = new ChampionPick(JarvanIV, false, false);
		ChampionPick azir = new ChampionPick(Azir, false, false);
		ChampionPick kogmaw = new ChampionPick(KogMaw, false, false);
		ChampionPick lulu = new ChampionPick(Lulu, false, false);
		kSession.insert(aphelios);
		kSession.insert(azir);
		kSession.insert(jarvan);
		kSession.insert(tahmkench);
		kSession.insert(veigar);
		kSession.insert(ornn);
		kSession.insert(lulu);
		kSession.insert(sylas);
		kSession.insert(leesin);
		kSession.insert(kogmaw);
		kSession.fireAllRules();
		assertTrue(championSelect.isMyTopPicked());
		assertTrue(championSelect.isMyJunglePicked());
		assertTrue(championSelect.isMyMidPicked());
		assertTrue(championSelect.isMyBotPicked());
		assertTrue(championSelect.isMySupportPicked());
		assertTrue(championSelect.isEnemyTopPicked());
		assertTrue(championSelect.isEnemyJunglePicked());
		assertTrue(championSelect.isEnemyMidPicked());
		assertTrue(championSelect.isEnemyBotPicked());
		assertTrue(championSelect.isEnemySupportPicked());
		assertEquals(Position.top, sylas.getPosition());
		assertEquals(Position.jungle, leesin.getPosition());
		assertEquals(Position.mid, veigar.getPosition());
		assertEquals(Position.bot, aphelios.getPosition());
		assertEquals(Position.support, tahmkench.getPosition());
		assertEquals(Position.top, ornn.getPosition());
		assertEquals(Position.jungle, jarvan.getPosition());
		assertEquals(Position.mid, azir.getPosition());
		assertEquals(Position.bot, kogmaw.getPosition());
		assertEquals(Position.support, lulu.getPosition());
	}
	
	@Test
	public void fncG2Game2() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect();
		kSession.insert(championSelect);
		ChampionPick ornn = new ChampionPick(Ornn, true, false);
		ChampionPick trundle = new ChampionPick(Trundle, true, false);
		ChampionPick kassadin = new ChampionPick(Kassadin, true, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, true, false);
		ChampionPick tahmkench = new ChampionPick(TahmKench, true, false);
		ChampionPick zac = new ChampionPick(Zac, false, false);
		ChampionPick jarvan = new ChampionPick(JarvanIV, false, false);
		ChampionPick azir = new ChampionPick(Azir, false, false);
		ChampionPick kogmaw = new ChampionPick(KogMaw, false, false);
		ChampionPick lulu = new ChampionPick(Lulu, false, false);
		kSession.insert(aphelios);
		kSession.insert(azir);
		kSession.insert(jarvan);
		kSession.insert(tahmkench);
		kSession.insert(ornn);
		kSession.insert(zac);
		kSession.insert(kogmaw);
		kSession.insert(trundle);
		kSession.insert(kassadin);
		kSession.insert(lulu);
		kSession.fireAllRules();
		assertTrue(championSelect.isMyTopPicked());
		assertTrue(championSelect.isMyJunglePicked());
		assertTrue(championSelect.isMyMidPicked());
		assertTrue(championSelect.isMyBotPicked());
		assertTrue(championSelect.isMySupportPicked());
		assertTrue(championSelect.isEnemyTopPicked());
		assertTrue(championSelect.isEnemyJunglePicked());
		assertTrue(championSelect.isEnemyMidPicked());
		assertTrue(championSelect.isEnemyBotPicked());
		assertTrue(championSelect.isEnemySupportPicked());
		assertEquals(Position.top, ornn.getPosition());
		assertEquals(Position.jungle, trundle.getPosition());
		assertEquals(Position.mid, kassadin.getPosition());
		assertEquals(Position.bot, aphelios.getPosition());
		assertEquals(Position.support, tahmkench.getPosition());
		assertEquals(Position.top, zac.getPosition());
		assertEquals(Position.jungle, jarvan.getPosition());
		assertEquals(Position.mid, azir.getPosition());
		assertEquals(Position.bot, kogmaw.getPosition());
		assertEquals(Position.support, lulu.getPosition());
	}

	@Test
	public void fncG2Game3() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect();
		kSession.insert(championSelect);
		ChampionPick zac = new ChampionPick(Zac, true, false);
		ChampionPick gragas = new ChampionPick(Gragas, true, false);
		ChampionPick azir = new ChampionPick(Azir, true, false);
		ChampionPick missfortune = new ChampionPick(MissFortune, true, false);
		ChampionPick braum = new ChampionPick(Braum, true, false);
		ChampionPick ornn = new ChampionPick(Ornn, false, false);
		ChampionPick trundle = new ChampionPick(Trundle, false, false);
		ChampionPick corki = new ChampionPick(Corki, false, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, false, false);
		ChampionPick janna = new ChampionPick(Janna, false, false);
		kSession.insert(azir);
		kSession.insert(aphelios);
		kSession.insert(corki);
		kSession.insert(missfortune);
		kSession.insert(gragas);
		kSession.insert(ornn);
		kSession.insert(trundle);
		kSession.insert(braum);
		kSession.insert(zac);
		kSession.insert(janna);
		kSession.fireAllRules();
		assertTrue(championSelect.isMyTopPicked());
		assertTrue(championSelect.isMyJunglePicked());
		assertTrue(championSelect.isMyMidPicked());
		assertTrue(championSelect.isMyBotPicked());
		assertTrue(championSelect.isMySupportPicked());
		assertTrue(championSelect.isEnemyTopPicked());
		assertTrue(championSelect.isEnemyJunglePicked());
		assertTrue(championSelect.isEnemyMidPicked());
		assertTrue(championSelect.isEnemyBotPicked());
		assertTrue(championSelect.isEnemySupportPicked());
		assertEquals(Position.top, zac.getPosition());
		assertEquals(Position.jungle, gragas.getPosition());
		assertEquals(Position.mid, azir.getPosition());
		assertEquals(Position.bot, missfortune.getPosition());
		assertEquals(Position.support, braum.getPosition());
		assertEquals(Position.top, ornn.getPosition());
		assertEquals(Position.jungle, trundle.getPosition());
		assertEquals(Position.mid, corki.getPosition());
		assertEquals(Position.bot, aphelios.getPosition());
		assertEquals(Position.support, janna.getPosition());
	}

}
