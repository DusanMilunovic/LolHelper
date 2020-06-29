package com.lolpicker.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.CheckRelationship;
import com.lolpicker.model.Enemy;
import com.lolpicker.model.Friend;

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
		Friend f1 = new Friend("Aatrox", "Mordekaiser");
		Friend f4 = new Friend("Mordekaiser", "Hlebio");
		Friend f5 = new Friend("Hlebio", "Marku");
		Friend f9 = new Friend("Marku", "zec");
		Friend f6 = new Friend("Marku", "rku");
		Friend f7 = new Friend("Marku", "dlfkjs");
		Friend f8 = new Friend("Marku", "lk");
		Friend f10 = new Friend("Marku", "Volibear");
		Enemy f2 = new Enemy("Hlebio", "Amumu");
		Friend f3 = new Friend("Amumu", "Krom");
		Friend f11 = new Friend("Krom", "Volibear");
		int maxDepth = 20;
		for (int i = 0; i < maxDepth; i++) {
			kSession.insert(i);
		}
		kSession.insert(new CheckRelationship("Aatrox", "Volibear"));
		kSession.insert(f1);
		kSession.insert(f4);
		kSession.insert(f5);
		kSession.insert(f6);
		kSession.insert(f7);
		kSession.insert(f8);
		kSession.insert(f9);
		kSession.insert(f2);
		kSession.insert(f3);
		kSession.insert(f10);
		kSession.insert(f11);
		kSession.fireAllRules();
	}

}
