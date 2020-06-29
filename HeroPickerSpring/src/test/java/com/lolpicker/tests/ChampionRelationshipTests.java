package com.lolpicker.tests;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.CheckRelationship;
import com.lolpicker.model.Enemy;
import com.lolpicker.model.Friend;
import com.lolpicker.service.RelationshipService;

public class ChampionRelationshipTests {

	KieSession kSession = null;
	static KieContainer kieContainer;

	@BeforeClass
	public static void beforeClass() {
		KieServices ks = KieServices.Factory.get();
		kieContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroRelationsKjar", "0.0.1-SNAPSHOT"));
	}

	@Test
	public void friendsTest() {
		KieSession kSession = kieContainer.newKieSession();
		RelationshipService.populateRelations(kSession, 6);
		CheckRelationship cr = new CheckRelationship("Garen", "Lux");
		kSession.insert(cr);
		kSession.fireAllRules();
		assertTrue(cr.getRelationship().equals("Friends"));
	}

	@Test
	public void enemiesTest() {
		KieSession kSession = kieContainer.newKieSession();
		RelationshipService.populateRelations(kSession, 6);
		CheckRelationship cr = new CheckRelationship("Riven", "Yasuo");
		kSession.insert(cr);
		kSession.fireAllRules();
		assertTrue(cr.getRelationship().equals("Enemies"));
	}
	
	@Test
	public void enemyTriangleTest() {
		KieSession kSession = kieContainer.newKieSession();
		RelationshipService.populateRelations(kSession, 6);
		CheckRelationship cr1 = new CheckRelationship("Mordekaiser", "LeBlanc");
		kSession.insert(cr1);
		kSession.fireAllRules();
		assertTrue(cr1.getRelationship().equals("Enemies"));
		CheckRelationship cr2 = new CheckRelationship("Mordekaiser", "Vladimir");
		kSession.insert(cr2);
		kSession.fireAllRules();
		assertTrue(cr2.getRelationship().equals("Friends"));
	}

	@Test
	public void indirectFriendsTest() {
		KieSession kSession = kieContainer.newKieSession();
		RelationshipService.populateRelations(kSession, 6);
		CheckRelationship cr = new CheckRelationship("Garen", "Shyvana");
		kSession.insert(cr);
		kSession.fireAllRules();
		assertTrue(cr.getRelationship().equals("Friends"));
	}

	@Test
	public void indirectEnemiesTest() {
		KieSession kSession = kieContainer.newKieSession();
		RelationshipService.populateRelations(kSession, 6);
		CheckRelationship cr1 = new CheckRelationship("Rakan", "Zed");
		kSession.insert(cr1);
		kSession.fireAllRules();
		assertTrue(cr1.getRelationship().equals("Enemies"));
		CheckRelationship cr2 = new CheckRelationship("Zed", "Akali");
		kSession.insert(cr2);
		kSession.fireAllRules();
		assertTrue(cr2.getRelationship().equals("Enemies"));
	}
	
	@Test
	public void enemyOfMyFriend() {
		KieSession kSession = kieContainer.newKieSession();
		RelationshipService.populateRelations(kSession, 6);
		CheckRelationship cr1 = new CheckRelationship("Illaoi", "MissFortune");
		kSession.insert(cr1);
		kSession.fireAllRules();
		assertTrue(cr1.getRelationship().equals("Enemies"));
		CheckRelationship cr2 = new CheckRelationship("Rumble", "Caitlyn");
		kSession.insert(cr2);
		kSession.fireAllRules();
		assertTrue(cr2.getRelationship().equals("Enemies"));
	}

	@Test
	public void enemyOfMyEnemy() {
		KieSession kSession = kieContainer.newKieSession();
		RelationshipService.populateRelations(kSession, 6);
		CheckRelationship cr1 = new CheckRelationship("Anivia", "Ashe");
		kSession.insert(cr1);
		kSession.fireAllRules();
		assertTrue(cr1.getRelationship().equals("Friends"));
	}
}
