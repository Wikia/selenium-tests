package com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Author: Artur Dwornik
 * Date: 02.04.13
 * Time: 17:31
 */
public class WikiHomePage extends BasePageObject {

    @FindBy(css="#HOME_TOP_LEADERBOARD")
    private WebElement wikiHomePageSpecificElement;

    public WikiHomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Check if current page is in fact home page of wiki
     */
    public void verifyThisIsWikiHomePage() {
        waitForElementByElement(wikiHomePageSpecificElement);
    }
}
