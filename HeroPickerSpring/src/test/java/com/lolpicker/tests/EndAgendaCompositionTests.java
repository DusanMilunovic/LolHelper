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
import com.lolpicker.model.CompositionStrongPoint;
import com.lolpicker.model.CompositionType;
import com.lolpicker.model.Position;


public class EndAgendaCompositionTests {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	@BeforeClass
	public static void beforeClass() {
		KieServices ks = KieServices.Factory.get();
		kieContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroPickerKjar", "0.0.1-SNAPSHOT"));
	}
	
	@Test
	public void earlyVsLateTeamComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick fiora = new ChampionPick(Fiora, true, false);
		ChampionPick graves = new ChampionPick(Graves, true, false);
		ChampionPick veigar = new ChampionPick(Veigar, true, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, true, false);
		ChampionPick janna = new ChampionPick(Janna, true, false);
		ChampionPick camille = new ChampionPick(Camille, false, false);
		ChampionPick jarvan = new ChampionPick(JarvanIV, false, false);
		ChampionPick azir = new ChampionPick(Azir, false, false);
		ChampionPick draven = new ChampionPick(Draven, false, false);
		ChampionPick blitzcrank = new ChampionPick(Blitzcrank, false, false);
		kSession.insert(aphelios);
		kSession.insert(azir);
		kSession.insert(jarvan);
		kSession.insert(janna);
		kSession.insert(veigar);
		kSession.fireAllRules();
		kSession.insert(camille);
		kSession.insert(blitzcrank);
		kSession.insert(fiora);
		kSession.insert(graves);
		kSession.insert(draven);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertEquals(CompositionStrongPoint.lategame, championSelect.getMyTeamStrongPoint());
		assertEquals(CompositionStrongPoint.earlygame, championSelect.getEnemyTeamStrongPoint());
	}

	@Test
	public void midVsBalancedTeamComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick aatrox = new ChampionPick(Aatrox, true, false);
		ChampionPick graves = new ChampionPick(Graves, true, false);
		ChampionPick akali = new ChampionPick(Akali, true, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, true, false);
		ChampionPick alistar = new ChampionPick(Alistar, true, false);
		ChampionPick camille = new ChampionPick(Camille, false, false);
		ChampionPick amumu = new ChampionPick(Amumu, false, false);
		ChampionPick diana = new ChampionPick(Diana, false, false);
		ChampionPick ezreal = new ChampionPick(Ezreal, false, false);
		ChampionPick blitzcrank = new ChampionPick(Blitzcrank, false, false);
		kSession.insert(aphelios);
		kSession.insert(diana);
		kSession.insert(amumu);
		kSession.insert(alistar);
		kSession.insert(akali);
		kSession.fireAllRules();
		kSession.insert(camille);
		kSession.insert(blitzcrank);
		kSession.insert(aatrox);
		kSession.insert(graves);
		kSession.insert(ezreal);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertEquals(CompositionStrongPoint.balanced, championSelect.getMyTeamStrongPoint());
		assertEquals(CompositionStrongPoint.midgame, championSelect.getEnemyTeamStrongPoint());
	}
}
