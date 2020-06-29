package com.lolpicker;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.lolpicker.service.RelationshipService;

@SpringBootApplication
public class HeroPickerSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroPickerSpringApplication.class, args);
	}

	@Bean(name="heroPickerContainer")
	public KieContainer kieContainerHeroPicker() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroPickerKjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

	@Bean(name="heroPickerSession")
	public KieSession kieSessionHeroPicker() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroPickerKjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		KieSession kSession = kContainer.newKieSession();
		return kSession; 
	}

	@Bean(name="relationshipsContainer")
	public KieContainer kieContainerRelationships() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroRelationsKjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		return kContainer;
	}

	@Bean(name="relationshipsSession")
	public KieSession kieSessionRelationships() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.lolpicker", "HeroRelationsKjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10_000);
		KieSession kSession = kContainer.newKieSession();
		RelationshipService.populateRelations(kSession, 100);
		return kSession; 
	}

}
