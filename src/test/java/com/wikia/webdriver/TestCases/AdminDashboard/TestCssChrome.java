package com.wikia.webdriver.TestCases.AdminDashboard;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.AdminDashboard.AdminDashboardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;

public class TestCssChrome extends TestTemplate {
	@Test(groups = {"AdminDashboard001", "AdminDashboard"})
	public void goToCssChrome() {
		AdminDashboardPageObject pageObject = new AdminDashboardPageObject(driver);

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

		pageObject.openAdminDashboard();

		pageObject.clickCssChromeIcon();

		login.logOut(driver);
	}

	@Test(groups = {"AdminDashboard002", "AdminDashboard"})
	public void openMediawikiWikiaCss() {
		AdminDashboardPageObject pageObject = new AdminDashboardPageObject(driver);

		SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff);

		pageObject.openMediawikiWikiaCss();

		login.logOut(driver);
	}

	@Test(groups = {"AdminDashboard003", "AdminDashboard"})
	public void openMediawikiWikiaCssAsAnon() {
		AdminDashboardPageObject pageObject = new AdminDashboardPageObject(driver);

		pageObject.openMediawikiWikiaCss();

		//TODO check edit button & edit page
	}
}
