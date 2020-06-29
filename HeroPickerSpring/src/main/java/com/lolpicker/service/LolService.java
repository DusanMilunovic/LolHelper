package com.lolpicker.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lolpicker.HeroPickerSpringApplication;
import com.lolpicker.dto.ChampionSelectAndPicksDto;
import com.lolpicker.dto.CompositionRuleDto;
import com.lolpicker.dto.MessageDto;
import com.lolpicker.model.ChampionPick;
import com.lolpicker.model.ChampionSelect;
import com.lolpicker.model.Position;

@Service
public class LolService {

	@Autowired
	@Qualifier("heroPickerContainer")
	private KieContainer kieContainer;

	
	public ChampionSelectAndPicksDto getPicks(ArrayList<ChampionPick> championPicks, Position position) {
		KieSession kSession = kieContainer.newKieSession();
    	ChampionSelect cs = new ChampionSelect(position);
    	kSession.insert(cs);
    	for (ChampionPick cp: championPicks) {
    		kSession.insert(cp);
    	}
    	kSession.fireAllRules();
    	ChampionSelectAndPicksDto cscps = new ChampionSelectAndPicksDto();
    	cscps.setChampionSelect(cs);
    	cscps.setPicks(championPicks);
		return cscps;
	}

	public ChampionSelectAndPicksDto getAdvice(ArrayList<ChampionPick> championPicks, Position position) {
		KieSession kSession = kieContainer.newKieSession();
    	ChampionSelect cs = new ChampionSelect(position);
    	kSession.insert(cs);
    	for (ChampionPick cp: championPicks) {
    		kSession.insert(cp);
    	}
    	kSession.fireAllRules();
    	kSession.getAgenda().getAgendaGroup("end").setFocus();
		kSession.fireAllRules();
    	ChampionSelectAndPicksDto cscps = new ChampionSelectAndPicksDto();
    	cscps.setChampionSelect(cs);
    	cscps.setPicks(championPicks);
		return cscps;
	}

	public MessageDto addRule(CompositionRuleDto compositionRule) {
        InputStream template = HeroPickerSpringApplication.class.getResourceAsStream("/templates/compositionRule.drt");
        
        DataProvider dataProvider = new ArrayDataProvider(new String[][]{
            new String[]{String.valueOf(compositionRule.getComposition()), String.valueOf(compositionRule.getAdChampions()),
            		String.valueOf(compositionRule.getApChampions()), String.valueOf(compositionRule.getTankChampions()),
            		String.valueOf(compositionRule.getHealerChampions()), String.valueOf(compositionRule.getPokeChampions()),
            		compositionRule.getCounters().get(0),
            		compositionRule.getCounters().get(1),
            		compositionRule.getCounters().get(2),
            		compositionRule.getCounters().get(3),
            		compositionRule.getCounters().get(4),
            		compositionRule.getCounters().get(5),
            		compositionRule.getCounters().get(6),
            		compositionRule.getCounters().get(7),
            		}
        });
        
        DataProviderCompiler converter = new DataProviderCompiler();
        String drl = converter.compile(dataProvider, template);
		try {
			FileWriter myWriter = new FileWriter("../HeroPickerKjar/src/main/resources/com/lolpicker/rules/customRule" + compositionRule.getComposition() + ".drl");
			myWriter.write(drl);
			myWriter.close();
			InvocationRequest request = new DefaultInvocationRequest();
			request.setPomFile(new File("../HeroPickerKjar/pom.xml"));
			request.setGoals(Arrays.asList("clean", "install"));
			System.out.println(System.getenv("MAVEN_HOME"));
			File f = new File(System.getenv("MAVEN_HOME"));
			System.out.println(f.getAbsolutePath());
			Invoker invoker = new DefaultInvoker();
			invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
			invoker.execute( request );
			return new MessageDto("Success");
		} catch (IOException e) {
			return new MessageDto("Failed to write to file");
		} catch (MavenInvocationException e) {
			// TODO Auto-generated catch block
			return new MessageDto("Maven install failed");
		}
	}

}
