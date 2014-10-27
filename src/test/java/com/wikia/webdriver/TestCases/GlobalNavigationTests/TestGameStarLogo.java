package com.wikia.webdriver.TestCases.GlobalNavigationTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNav.VenusGlobalNavPageObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * Bartosz 'V.' Bentkowski
 *
 * @ownership Consumer
 */
public class TestGameStarLogo extends NewTestTemplate {

	private final static String deWikiName = "de.gta";
	private static final Dimension SHOW_LOGO_RESOLUTION = new Dimension(1300, 900);
	private static final Dimension HIDE_LOGO_RESOLUTION = new Dimension(1299, 900);

	@Test(groups = {"TestGameStarLogo_001", "GameStarLogo", "GlobalNav"})
	public void TestGameStarLogo_001_gameStarLogoPresentOnBigResolution() {
		HomePageObject homePage = new HomePageObject(driver);
		VenusGlobalNavPageObject globalNav = new VenusGlobalNavPageObject(driver);
		homePage.openWikiPage(urlBuilder.getUrlForWiki(deWikiName));
		homePage.resizeWindow(SHOW_LOGO_RESOLUTION);

		Assertion.assertTrue(globalNav.isGameStarLogoDisplayed(), "GameStar Logo should be visible");
	}

	@Test(groups = {"TestGameStarLogo_002", "GameStarLogo", "GlobalNav"})
	public void TestGameStarLogo_002_gameStarLogoNotPresentOnSmallResolution() {
		HomePageObject homePage = new HomePageObject(driver);
		VenusGlobalNavPageObject globalNav = new VenusGlobalNavPageObject(driver);
		homePage.openWikiPage(urlBuilder.getUrlForWiki(deWikiName));
		homePage.resizeWindow(HIDE_LOGO_RESOLUTION);

		Assertion.assertFalse(globalNav.isGameStarLogoDisplayed(), "GameStar Logo shouldn't be visible");
	}
}
