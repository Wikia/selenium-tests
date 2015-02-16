package com.wikia.webdriver.testcases.globalnavigationtests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

public class GlobalNavigationScrollAction extends NewTestTemplate{
	
	/**
	 * @author Michal 'justnpT' Nowierski
	 * 
	 * Test scrollDown
	 * 1. Go to Special:WikiActivity and scroll down to the bottom
	 * 2. Make sure that navbar is still attached on the top
	 */
	@Test(
		groups = {"globalNav", "globalNav_scrollDown"}
	)
	public void scrollDown() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
	    SpecialWikiActivityPageObject wikiActivity = base.openSpecialWikiActivity(wikiURL);
	    wikiActivity.verifyGlobalNavigation();
	    wikiActivity.scrollToFooter();
	    wikiActivity.verifyGlobalNavigation();
	}

}
