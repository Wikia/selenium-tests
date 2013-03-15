package com.wikia.webdriver.TestCases.MarketingToolboxTests;

import com.wikia.webdriver.Common.Core.Assertion;
import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.MarketingToolbox.DashboarPageObject;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: kvas
 * Date: 15.03.13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class DashboardTests extends TestTemplate{
    @Test(groups = {"MarketingToolbox001", "MarketingToolbox"})
    public void dashboardSelectVertical() {

        DashboarPageObject pageObject = new DashboarPageObject(driver);
        CommonFunctions.logOut(driver);
        CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff, driver);
        pageObject.openDashboard();
        pageObject.selectLang("en");
        pageObject.clickHub();
        pageObject.clickVertical(DashboarPageObject.vertical.Video_games);


        CommonFunctions.logOut(driver);
        CommonFunctions.logIn(Properties.userNameStaff, Properties.passwordStaff, driver);
        pageObject.openDashboard();
        Assertion.assertTrue(pageObject.checkActiveVertical(DashboarPageObject.vertical.Video_games));
    }
}
