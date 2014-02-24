package com.wikia.webdriver.TestCases.MarketingToolboxTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.NewTestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MarketingToolbox.DashBoardPageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.Special.Login.SpecialUserLoginPageObject;
import org.testng.annotations.Test;


public class DashboardTests extends NewTestTemplate {
    @Test(groups = {"MarketingToolbox001", "MarketingToolbox"})
    public void dashboardSelectVertical() {

    	SpecialUserLoginPageObject login = new SpecialUserLoginPageObject(driver);
    	login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff, wikiURL);

    	DashBoardPageObject pageObject = new DashBoardPageObject(driver);
        login.logOut(driver);


        pageObject.openDashboard();
        pageObject.selectLang("en");
        pageObject.clickHub();
        pageObject.clickVertical(DashBoardPageObject.vertical.Video_games);


        login.logOut(driver);

        login.loginAndVerify(Properties.userNameStaff, Properties.passwordStaff, wikiURL);

        pageObject.openDashboard();
        Assertion.assertTrue(pageObject.checkActiveVertical(DashBoardPageObject.vertical.Video_games));
    }
}
