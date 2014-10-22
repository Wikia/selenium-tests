package com.wikia.webdriver.TestCases.GlobalNavigationTests;


import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.ImageUtilities.ImageComparison;
import com.wikia.webdriver.Common.Core.ImageUtilities.Shooter;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.GlobalNav.GlobalNavPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.HomePageObject;
import org.testng.annotations.Test;

import java.io.File;

public class CompareScreenshotsForHubsMenu extends NewTestTemplate {

	/**
	 * Test is taking screenshots of of hubs menu in global navigation and comparing to the expected designs from
	 * resources folder located in 'Baseline' folder
	 */
	@Test(groups = {"HubsMenu_001_comapreScreenshotsForHubsMenu", "GlobalNav"})
	public void compareScreenshotForHubsMenu() {


		HomePageObject homePage = new HomePageObject(driver);
		homePage.openWikiPage(urlBuilder.getUrlForWiki("serowiec"));

		boolean failed = false;

		for (GlobalNavPageObject.Hub hubName : GlobalNavPageObject.Hub.values()) {
			homePage.getGlobalNav().openHub(hubName);
			failed = takeScreenshotAndCompare(homePage.getGlobalNav().getMenuScreenShotArea(), hubName.getLabelText());
		}

		//Throw exception if any test fails, just to mark whole test as failed
		if (failed) {
			throw new AssertionError();
		}
	}

	private boolean takeScreenshotAndCompare(org.openqa.selenium.WebElement element, String expectedFileName) {
		Shooter shooter = new Shooter();

		ImageComparison comparator = new ImageComparison();

		File currentFile = shooter.captureWebElement(element, driver);
		String expectedFilePath = ClassLoader.getSystemResource("Baseline/" + expectedFileName + ".png")
				.getPath();
		File expectedFile = new File(expectedFilePath);
		try {
			Assertion
					.assertTrue(comparator.areFilesTheSame(currentFile, expectedFile));
			return false;
		} catch (AssertionError e) {
			PageObjectLogging
					.log("Design is not as expected for: " + expectedFileName, "Expected: " + expectedFilePath, false, driver);
			return true;
		}
	}
}
