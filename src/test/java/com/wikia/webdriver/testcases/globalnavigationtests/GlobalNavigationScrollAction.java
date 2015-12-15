package com.wikia.webdriver.testcases.globalnavigationtests;

import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialWikiActivityPageObject;

import org.testng.annotations.Test;

public class GlobalNavigationScrollAction extends NewTestTemplate{

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
