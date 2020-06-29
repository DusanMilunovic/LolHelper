package com.lolpicker.service;

import java.io.FileReader;
import java.io.IOException;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lolpicker.model.CheckRelationship;
import com.lolpicker.model.Enemy;
import com.lolpicker.model.Friend;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

@Service
public class RelationshipService {

	@Autowired
	@Qualifier("relationshipsSession")
	private KieSession kSession;

	public String checkRelationship(String champion1, String champion2) {
		CheckRelationship cr = new CheckRelationship(champion1, champion2); 
		kSession.insert(cr);
		kSession.fireAllRules();
		return cr.getRelationship();
	}
	
	public static void populateRelations(KieSession kSession, int maxDepth) {
		for (int i = 0; i < maxDepth; i++) {
			kSession.insert(i);
		}
		String fileName = "src/main/resources/relationships.csv";
		CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
		CSVReader reader = null;
		try {
			reader = new CSVReaderBuilder(new FileReader(fileName)).withCSVParser(parser).build();
			String[] line;
			while ((line = reader.readNext()) != null) {
				String actor = line[0].trim();
				String friends = line[1];
				if (!friends.equals(" ")) {
					String[] friendsArray = friends.split(",");
					for (String friend: friendsArray) {
						kSession.insert(new Friend(actor, friend.trim()));
					}
				}
				String enemies = line[2];
				if (!enemies.equals(" ")) {
					String[] enemiesArray = enemies.split(",");
					for (String enemy: enemiesArray) {
						kSession.insert(new Enemy(actor, enemy.trim()));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
