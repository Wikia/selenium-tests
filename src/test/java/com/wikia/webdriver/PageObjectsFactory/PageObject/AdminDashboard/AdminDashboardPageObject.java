package com.wikia.webdriver.PageObjectsFactory.PageObject.AdminDashboard;

import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.WebDriver;

public class AdminDashboardPageObject extends BasePageObject {

    public AdminDashboardPageObject(WebDriver driver) {
        super(driver);
    }

    public void openAdminDashboard() {
        getUrl(Global.DOMAIN + "wiki/Special:AdminDashboard");
        PageObjectLogging.log("openAdminDashboard", "admin dashboard opened", true);
    }
}
