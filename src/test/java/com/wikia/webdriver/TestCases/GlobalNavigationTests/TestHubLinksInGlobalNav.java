package com.wikia.webdriver.TestCases.GlobalNavigationTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNav.VenusGlobalNavPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 * @author Bogna 'bognix' Knychała
 * @ownership Consumer
 */
public class TestHubLinksInGlobalNav extends NewTestTemplate {

	@Test(
		groups = {"TestHubLinksInGlobalNav_001"}

	)
	public void TestHubLinksInGlobalNav_001_clickHubsLinks() {
		HomePageObject homePage = new HomePageObject(driver);
		homePage.getUrl(urlBuilder.getUrlForWiki("muppet"));
		VenusGlobalNavPageObject globalNav = homePage.getVenusGlobalNav();
		globalNav.openHubsMenuViaHover();
		Assertion.assertTrue(globalNav.isHubsMenuOpened());

		for (VenusGlobalNavPageObject.Hub hubName : VenusGlobalNavPageObject.Hub.values()) {
			WebElement hub = globalNav.openHub(hubName);
			String link = globalNav.getHubLink(hub);
			hub.click();
			Assertion.assertEquals(driver.getCurrentUrl(), link);
		}
	}
}
