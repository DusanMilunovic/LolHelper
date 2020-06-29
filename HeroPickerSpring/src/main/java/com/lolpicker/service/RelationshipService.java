package com.lolpicker.service;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lolpicker.model.CheckRelationship;
import com.lolpicker.model.Enemy;
import com.lolpicker.model.Friend;

@Service
public class RelationshipService {

	@Autowired
	@Qualifier("relationshipsSession")
	private KieSession kSession;

	public String checkRelationship(String champion1, String champion2) {
		CheckRelationship cr = new CheckRelationship(champion1, champion2); 
		kSession.insert(cr);
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
		return cr.getRelationship();
	}

}
