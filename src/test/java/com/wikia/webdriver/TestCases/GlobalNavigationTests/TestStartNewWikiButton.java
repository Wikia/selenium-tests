package com.wikia.webdriver.TestCases.GlobalNavigationTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.CreateNewWiki.CreateNewWikiPageObjectStep1;

public class TestStartNewWikiButton extends NewTestTemplate {

	@Test(groups={"TestStartNewWikiButton_001"})
	public void TestStartNewWikiButton_001 () {
		HomePageObject homePage = new HomePageObject(driver);
		homePage.getUrl(urlBuilder.getUrlForWiki("muppet"));
		CreateNewWikiPageObjectStep1 createNewWikiPage = homePage.getVenusGlobalNav().clickStartNewWiki();

		Assertion.assertTrue(createNewWikiPage.verifyPage());
	}

}
