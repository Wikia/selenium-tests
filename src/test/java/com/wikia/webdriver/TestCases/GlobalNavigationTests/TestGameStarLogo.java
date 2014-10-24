package com.wikia.webdriver.TestCases.GlobalNavigationTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

/**
 * Bartosz 'V.' Bentkowski
 *
 * @ownership Consumer
 */
public class TestGameStarLogo extends NewTestTemplate {

	private final static String wikiName = "de.gta";
	private final static Dimension bigResolution = new Dimension(1400, 900);
	private final static Dimension smallResolution = new Dimension(1000, 900);

	@Test(groups = {"TestGameStarLogo_001", "TestGameStarLogo"})
	public void TestGameStarLogo_001_gameStarLogoPresentOnBigResolution() {
		String url = urlBuilder.getUrlForPath(wikiName, "");
		HomePageObject homePage = new HomePageObject(driver);
		homePage.getUrl(url);
		homePage.resizeWindow(bigResolution);
		Assertion.assertTrue(homePage.getVenusGlobalNav().GameStarLinkDisplay(), "GameStar Logo should be visible");
	}

	@Test(groups = {"TestGameStarLogo_002", "TestGameStarLogo"})
	public void TestGameStarLogo_002_gameStarLogoNotPresentOnSmallResolution() {
		String url = urlBuilder.getUrlForPath(wikiName, "");
		HomePageObject homePage = new HomePageObject(driver);
		homePage.getUrl(url);
		homePage.resizeWindow(smallResolution);
		Assertion.assertFalse(homePage.getVenusGlobalNav().GameStarLinkDisplay(), "Game,tar Logo shouldn't be visible");
	}
}
