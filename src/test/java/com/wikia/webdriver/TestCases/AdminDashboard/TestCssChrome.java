package com.wikia.webdriver.TestCases.AdminDashboard;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdminDashboard.AdminDashboardPageObject;
import org.testng.annotations.Test;

public class TestCssChrome extends TestTemplate {
	@Test(groups = {"AdminDashboard001", "AdminDashboard"})
	public void goToCssChrome() {
		AdminDashboardPageObject pageObject = new AdminDashboardPageObject(driver);

		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff, driver);

		pageObject.openAdminDashboard();

		pageObject.clickCssChromeIcon();

		CommonFunctions.logOut(driver);
	}

	@Test(groups = {"AdminDashboard002", "AdminDashboard"})
	public void openMediawikiWikiaCss() {
		AdminDashboardPageObject pageObject = new AdminDashboardPageObject(driver);

		CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff, driver);

		pageObject.openMediawikiWikiaCss();

		CommonFunctions.logOut(driver);
	}

	@Test(groups = {"AdminDashboard003", "AdminDashboard"})
	public void openMediawikiWikiaCssAsAnon() {
		AdminDashboardPageObject pageObject = new AdminDashboardPageObject(driver);

		pageObject.openMediawikiWikiaCss();

		//TODO check edit button & edit page

		CommonFunctions.logOut(driver);
	}
}
