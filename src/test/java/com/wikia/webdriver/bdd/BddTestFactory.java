package com.wikia.webdriver.bdd;

import com.wikia.webdriver.bdd.integration.CucumberTestImpl;
import cucumber.api.CucumberOptions;
import org.testng.annotations.Factory;

import java.io.IOException;

public class BddTestFactory {

	@Factory
	public Object[] create() throws IOException {
		return new Object[] {
				new CucumberTestImpl(CrossWikiSearch.class),
				new CucumberTestImpl(CorporatePage.class),
		};
	}

	@CucumberOptions(format = "pretty", tags= { "@CrossWikiSearch" })
	public static class CrossWikiSearch { }

	@CucumberOptions(format = "pretty", tags= { "@CorporatePage" })
	public static class CorporatePage { }
}
