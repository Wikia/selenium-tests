package com.wikia.webdriver.TestCases.GlobalNavigationTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;

public class TestStartNewWikiButton extends NewTestTemplate {

	@Test
	public void TestStartNewWikiButton_001 () {
		HomePageObject homePage = new HomePageObject(driver);
		homePage.getUrl(urlBuilder.getUrlForWiki("muppet"));
		homePage.getVenusGlobalNav().clickStartNewWiki();

		Assertion.assertTrue(driver.getCurrentUrl().contains("Special:CreateNewWiki"));

	}

}
