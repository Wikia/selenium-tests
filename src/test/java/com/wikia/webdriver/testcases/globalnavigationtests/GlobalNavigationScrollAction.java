package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

import org.testng.annotations.Test;

/**
 * @ownership Content X-Wing
 */
public class GlobalNavigationScrollAction extends NewTestTemplate{
	
	/**
	 * @author Michal 'justnpT' Nowierski
	 * 
	 * Test scrollDown
	 * 1. Go to Special:WikiActivity and scroll down to the bottom
	 * 2. Make sure that navbar is still attached on the top
	 */
	@Test(
		groups = {"GlobalNav", "GlobalNav_scrollDown"}
	)
	public void scrollDown() {
		WikiBasePageObject base = new WikiBasePageObject(driver);
	    SpecialWikiActivityPageObject wikiActivity = base.openSpecialWikiActivity();
	    wikiActivity.verifyGlobalNavigation();
	    wikiActivity.scrollToFooter();
	    wikiActivity.verifyGlobalNavigation();
	}

}
