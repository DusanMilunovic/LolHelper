package com.lolpicker.main;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.lolpicker.model.ChampionPick;
import static com.lolpicker.model.Champion.*;

public class Main {

	public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.getKieClasspathContainer();
    	KieSession kSession = kContainer.newKieSession("ksession-rules");
    	ChampionPick cp1 = new ChampionPick(Aatrox, false, false);
    	kSession.insert(cp1);
    	kSession.fireAllRules();
//    	System.out.println(cp1.getClasses().get(0));
    	System.out.println(cp1.getStrongPoint());
	}

}
