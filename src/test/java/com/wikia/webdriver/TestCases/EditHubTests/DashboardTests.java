package com.wikia.webdriver.TestCases.EditHubTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.EditHub.DashBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DashboardTests extends NewTestTemplate {

	@DataProvider
	private final Object[][] provideHubUrl() {
		return new Object[][] {
				{urlBuilder.getUrlForWiki("gameshub")},
				{urlBuilder.getUrlForWiki("movieshub")},
				{urlBuilder.getUrlForWiki("lifestylehub")}
		};
	}

	@Test(dataProvider = "provideHubUrl", groups = {"EditHub001", "EditHub"})
	public void dashboardSelectVertical(String hubUrl) {

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
		login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff, wikiURL);

		DashBoardPageObject pageObject = new DashBoardPageObject(driver);

		pageObject.openDashboard(hubUrl);
		pageObject.verifyCalendarAppears();
	}
}
