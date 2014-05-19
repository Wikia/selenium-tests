package com.wikia.webdriver.TestCases.EditHubTests;

import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialEditHubPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author Damian 'kvas' Jóźwiak
 * @ownership Consumer
 * 1. Open edit hub dashboard and check if calendar exists
 *
 */
public class DashboardTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@DataProvider
	private final Object[][] provideHubUrl() {
		return new Object[][] {
				{urlBuilder.getUrlForWiki("gameshub")},
				{urlBuilder.getUrlForWiki("movieshub")},
				{urlBuilder.getUrlForWiki("lifestylehub")}
		};
	}

	@Test(dataProvider = "provideHubUrl", groups = {"EditHub_001", "EditHub"})
	public void EditHub_001_dashboardSelectVertical(String hubUrl) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userName, credentials.password, wikiURL);

		SpecialEditHubPageObject pageObject = base.openSpecialEditHub(hubUrl);

		pageObject.verifyCalendarAppears();
	}
}
