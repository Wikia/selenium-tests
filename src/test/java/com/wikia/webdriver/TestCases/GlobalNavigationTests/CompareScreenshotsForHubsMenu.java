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
	@Test(groups = {"HubsMenu_001", "GlobalNav"})
	public void compareScreenshotForHubsMenu() {
		Shooter shooter = new Shooter();

		HomePageObject homePage = new HomePageObject(driver);
		homePage.openWikiPage("http://sandbox-qa02.serowiec.wikia.com/wiki/Serowiec_123_Wiki");

		ImageComparison comparator = new ImageComparison();
		boolean ifFailed = false;

		for (GlobalNavPageObject.Hub label : GlobalNavPageObject.Hub.values()) {
			homePage.getGlobalNav().openHub(label);
			File currentFile = shooter.captureWebElement(homePage.getGlobalNav().getMenuScreenShotArea(), driver);
			String expectedFilePath = ClassLoader.getSystemResource("Baseline/" + label.getLabelText() + ".png")
					.getPath();
			File expectedFile = new File(expectedFilePath);
			try {
				Assertion
						.assertTrue(comparator.areFilesTheSame(currentFile, expectedFile));
			} catch (AssertionError e) {
				ifFailed = true;
				PageObjectLogging
						.log("Design is not as expected for: " + label
								.getLabelText(), "Expected: " + expectedFilePath, false, driver);
			}
		}

		//Throw exception if any test fails, just to mark whole test as failed
		if (ifFailed) {
			throw new AssertionError();
		}
	}
}
