package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Ludwik on 2015-09-23.
 */
public class ContentReviewModule extends WikiBasePageObject {

    @FindBy(css = ".content-review-module")
    private WebElement contentReviewModule;

    public ContentReviewModule(WebDriver driver) {
        super(driver);
    }

    public boolean isModuleVisible(){
        try {
            wait.forElementVisible(contentReviewModule, 10, 1);
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }
}
