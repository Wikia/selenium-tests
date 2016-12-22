package com.wikia.webdriver.common.templates.fandom;

import org.openqa.selenium.Dimension;

/**
 * Created by wojtek on 22/12/2016.
 */
public class AdsFandomMobileTestTemplate extends AdsFandomTestTemplate {

    @Override
    public void setWindowSize() {
        driver.manage().window().setSize(new Dimension(360, 640));
    }
}
