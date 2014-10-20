package com.wikia.webdriver.TestCases.EditHubTests;

import com.wikia.webdriver.Common.DataProvider.HubsDataProvider;
import com.wikia.webdriver.Common.Properties.Credentials;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.SpecialEditHubPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.testng.annotations.Test;

/**
 * @author Damian 'kvas' Jóźwiak
 * @ownership Consumer
 * 1. Open edit hub dashboard and check if calendar exists
 *
 */
public class EditHubTests extends NewTestTemplate {

	Credentials credentials = config.getCredentials();

	@Test(groups = {"EditHub_001", "EditHub"},
			dataProviderClass = HubsDataProvider.class,
			dataProvider = "provideHubDBName")
	public void EditHub_001_dashboardSelectVertical(String hubDBName) {
		WikiBasePageObject base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameStaff, credentials.passwordStaff, wikiURL);
		SpecialEditHubPageObject pageObject = base.openSpecialEditHub(urlBuilder.getUrlForWiki(hubDBName));
		pageObject.verifyCalendarAppears();
	}
}
