package com.lolpicker.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ClassAndStrongPointTest.class, ChampionRelationshipTests.class, EndAgendaAdviceTests.class, EndAgendaCompositionTests.class,
		PositionTests.class, RoleChampionsTests.class, SecurityTest.class, TeamCompositionCountersTests.class, TeamCompositionTests.class })
public class AllTests {

}
