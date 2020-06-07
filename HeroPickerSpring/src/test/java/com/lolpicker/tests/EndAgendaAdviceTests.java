package com.lolpicker.tests;

import static com.lolpicker.model.Champion.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Position;


public class EndAgendaAdviceTests {

	KieSession kSession = null;
	static KieContainer kieContainer;
	
	public static boolean contains(ArrayList<String> strings, String string) {
		for (String advice: strings) {
			if (advice.equals(string)) {
				return true;
			}
		}
		return false;
	}
	
	@BeforeClass
	public static void beforeClass() {
		KieServices ks = KieServices.Factory.get();
		kieContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroPickerKjar", "0.0.1-SNAPSHOT"));
	}

	@Test
	public void enemyEarlyVsMyLateTeamComp() {
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
		kSession.insert(camille);
		kSession.insert(blitzcrank);
		kSession.insert(fiora);
		kSession.insert(graves);
		kSession.insert(draven);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team has an early game comp and your team does not. Try to play passive for the first 15 minutes, the goal of the early game is to survive it, not to win it."));
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Your team has a late game comp and enemy team does not. Try to extend the game until 35 minutes, because that is the time after which your composition shines."));
	}

	@Test
	public void myEarlyVsEnemyLateTeamComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick fiora = new ChampionPick(Fiora, false, false);
		ChampionPick graves = new ChampionPick(Graves, false, false);
		ChampionPick veigar = new ChampionPick(Veigar, false, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, false, false);
		ChampionPick janna = new ChampionPick(Janna, false, false);
		ChampionPick camille = new ChampionPick(Camille, true, false);
		ChampionPick jarvan = new ChampionPick(JarvanIV, true, false);
		ChampionPick azir = new ChampionPick(Azir, true, false);
		ChampionPick draven = new ChampionPick(Draven, true, false);
		ChampionPick blitzcrank = new ChampionPick(Blitzcrank, true, false);
		kSession.insert(aphelios);
		kSession.insert(azir);
		kSession.insert(jarvan);
		kSession.insert(janna);
		kSession.insert(veigar);
		kSession.insert(camille);
		kSession.insert(blitzcrank);
		kSession.insert(fiora);
		kSession.insert(graves);
		kSession.insert(draven);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team has a late game comp and your team does not. Try to finish the game before 35 minutes, because that is the time after which their composition shines."));
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Your team has an early game comp and enemy team does not. Try to play agressive for the first 15 minutes, you need to create a big lead to take you into the midgame."));
	}

	@Test
	public void myChampTankEnemyAdheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick fiora = new ChampionPick(Fiora, false, false);
		ChampionPick graves = new ChampionPick(Graves, false, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, false, false);
		ChampionPick malphite = new ChampionPick(Malphite, true, true);
		kSession.insert(aphelios);
		kSession.insert(malphite);
		kSession.insert(fiora);
		kSession.insert(graves);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple ad champions and your champion is a tank. Build armor to counter them."));
	}

	@Test
	public void myChampAdEnemyAdheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick fiora = new ChampionPick(Fiora, false, false);
		ChampionPick graves = new ChampionPick(Graves, false, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, false, false);
		ChampionPick camille = new ChampionPick(Camille, true, true);
		kSession.insert(aphelios);
		kSession.insert(camille);
		kSession.insert(fiora);
		kSession.insert(graves);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple ad champions and your champion is an ad carry. Build Guardian Angel as your 3rd or 4th item to increase survivability."));
	}

	@Test
	public void myChampApEnemyAdheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick fiora = new ChampionPick(Fiora, false, false);
		ChampionPick graves = new ChampionPick(Graves, false, false);
		ChampionPick aphelios = new ChampionPick(Aphelios, false, false);
		ChampionPick veigar = new ChampionPick(Veigar, true, true);
		kSession.insert(aphelios);
		kSession.insert(veigar);
		kSession.insert(fiora);
		kSession.insert(graves);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple ad champions and your champion is a tank. Build Zhonya's hourglass as your 3rd or 4th item to increase survivability."));
	}

	@Test
	public void myChampTankEnemyApheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick veigar = new ChampionPick(Veigar, false, false);
		ChampionPick ahri = new ChampionPick(Ahri, false, false);
		ChampionPick taliyah = new ChampionPick(Taliyah, false, false);
		ChampionPick maokai = new ChampionPick(Maokai, true, true);
		kSession.insert(ahri);
		kSession.insert(veigar);
		kSession.insert(taliyah);
		kSession.insert(maokai);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple ap champions and your champion is a tank. Build magic resistance to counter them."));
	}

	@Test
	public void myChampAdEnemyApheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick veigar = new ChampionPick(Veigar, false, false);
		ChampionPick ahri = new ChampionPick(Ahri, false, false);
		ChampionPick taliyah = new ChampionPick(Taliyah, false, false);
		ChampionPick aatrox = new ChampionPick(Aatrox, true, true);
		kSession.insert(ahri);
		kSession.insert(veigar);
		kSession.insert(taliyah);
		kSession.insert(aatrox);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple ap champions and your champion is an ad carry. Build Maw of Malmortius to increase survivability."));
	}

	@Test
	public void myChampApEnemyApheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick veigar = new ChampionPick(Veigar, false, false);
		ChampionPick ahri = new ChampionPick(Ahri, false, false);
		ChampionPick taliyah = new ChampionPick(Taliyah, false, false);
		ChampionPick cassiopeia = new ChampionPick(Cassiopeia, true, true);
		kSession.insert(ahri);
		kSession.insert(veigar);
		kSession.insert(taliyah);
		kSession.insert(cassiopeia);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple ap champions and your champion is an ap carry. Build Banshee's Veil as your 3rd or 4th item to increase survivability."));
	}

	@Test
	public void myChampAdEnemyTankheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick malphite = new ChampionPick(Malphite, false, false);
		ChampionPick poppy = new ChampionPick(Poppy, false, false);
		ChampionPick caitlyn = new ChampionPick(Caitlyn, true, true);
		kSession.insert(malphite);
		kSession.insert(poppy);
		kSession.insert(caitlyn);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple tank champions and your champion is an ad carry. Build Last Whisper to increase your damage against them."));
	}

	@Test
	public void myChampApEnemyTankheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick malphite = new ChampionPick(Malphite, false, false);
		ChampionPick poppy = new ChampionPick(Poppy, false, false);
		ChampionPick cassiopeia = new ChampionPick(Cassiopeia, true, true);
		kSession.insert(malphite);
		kSession.insert(poppy);
		kSession.insert(cassiopeia);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple tank champions and your champion is an ap carry. Build Liandry's Torment to increase your damage against them, and your damage in prolonged fights."));
	}

	@Test
	public void myChampAdEnemyHealerheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick soraka = new ChampionPick(Soraka, false, false);
		ChampionPick vladimir = new ChampionPick(Vladimir, false, false);
		ChampionPick ezreal = new ChampionPick(Ezreal, true, true);
		kSession.insert(soraka);
		kSession.insert(vladimir);
		kSession.insert(ezreal);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple healer champions and your champion is an ad carry. Build Mortal Reminder to decrease their healing."));
	}

	@Test
	public void myChampApEnemyHealerheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick soraka = new ChampionPick(Soraka, false, false);
		ChampionPick vladimir = new ChampionPick(Vladimir, false, false);
		ChampionPick malzahar = new ChampionPick(Malzahar, true, true);
		kSession.insert(soraka);
		kSession.insert(vladimir);
		kSession.insert(malzahar);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple healer champions and your champion is an ap carry. Build Morellonomicon to decrease their healing."));
	}

	@Test
	public void myChampTankEnemyPokeheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick ezreal = new ChampionPick(Ezreal, false, false);
		ChampionPick nidalee = new ChampionPick(Nidalee, false, false);
		ChampionPick drmundo = new ChampionPick(DrMundo, true, true);
		kSession.insert(ezreal);
		kSession.insert(nidalee);
		kSession.insert(drmundo);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple poke champions and your champion is a tank. Build Warmog's Armor to increase your health regeneration."));
	}

	@Test
	public void myChampAdEnemyPokeheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick ezreal = new ChampionPick(Ezreal, false, false);
		ChampionPick nidalee = new ChampionPick(Nidalee, false, false);
		ChampionPick draven = new ChampionPick(Draven, true, true);
		kSession.insert(ezreal);
		kSession.insert(nidalee);
		kSession.insert(draven);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple poke champions and your champion is an ad carry. Build life steal items (Bloodthirster or Death's Dance) increase your survivability."));
	}
	
	@Test
	public void myChampApEnemyPokeheavyComp() {
		KieSession kSession = kieContainer.newKieSession();
		ChampionSelect championSelect = new ChampionSelect(Position.top);
		kSession.insert(championSelect);
		ChampionPick ezreal = new ChampionPick(Ezreal, false, false);
		ChampionPick nidalee = new ChampionPick(Nidalee, false, false);
		ChampionPick velkoz = new ChampionPick(VelKoz, true, true);
		kSession.insert(ezreal);
		kSession.insert(nidalee);
		kSession.insert(velkoz);
		kSession.fireAllRules();
		kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
		assertTrue(contains(championSelect.getCompositionCountersAdvice(), "Enemy team composition contains multiple poke champions and your champion is an ap carry. Build Banshee's Veil to neglect some of their spells."));
	}
}
