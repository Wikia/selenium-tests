package com.wikia.webdriver.PageObjectsFactory.PageObject;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class BaseMonoBookPageObject extends BasePageObject {

    @FindBy(css = ".skin-monobook")
    protected WebElement skinClass;

    public BaseMonoBookPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openWikiPageWithMonobook() {
        String baseUrl = Domain;
        baseUrl = Domain + URLsContent.noexternals;
        getUrl(baseUrl);
        PageObjectLogging.log(
            "logOut",
            "page loads for more than 30 seconds", true
        );
        changeToMonoBook();
        PageObjectLogging.log(
            "logOut",
            "skin is changing for more then 30 seconds",
            true, driver
        );
    }

    public void changeToMonoBook() {
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.contains("?")) {
            getUrl(currentUrl+"&useskin=monobook");
        } else {
            getUrl(currentUrl+"?useskin=monobook");
        }
        verifySkinChanged();
    }

    public void verifySkinChanged() {
        waitForElementByElement(skinClass);
        PageObjectLogging.log(
            "skinChangedToMonoBook", "skin is changed to monobook", true
        );
    }
}
