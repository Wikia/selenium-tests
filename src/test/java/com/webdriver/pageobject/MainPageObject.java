package com.webdriver.pageobject;

import com.webdriver.common.core.PageWebDriver;
import com.webdriver.common.driverprovider.DriverProvider;

public class MainPageObject {

    protected PageWebDriver driver = DriverProvider.getActiveDriver();


    public void openUrl(String url) {
        driver.navigate().to(url);
    }

    public String getTitle() {
        return driver.getTitle();
    }

}
