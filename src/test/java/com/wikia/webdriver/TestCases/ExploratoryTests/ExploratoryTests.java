package com.wikia.webdriver.TestCases.ExploratoryTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Crawler;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Templates.TestTemplate;

public class ExploratoryTests extends TestTemplate {

	@Test(groups = { "ExploratoryTests_001", "ExploratoryTests" })
	public void ExploratoryTests_001_ExploreWikiPage() 
	{
		Crawler crawler = new Crawler(driver, Global.DOMAIN);
		crawler.prepareURLsForExploratoryTests(3);
		crawler.explore("FirstExploration");
		crawler.explore("SecondExploration");
		crawler.compareExplorationResults("FirstExploration", "SecondExploration");

	}
}