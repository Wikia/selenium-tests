/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class WikiBaseMonoBookPageObject extends BaseMonoBookPageObject {

    @FindBy(css = "#n-randompage a")
    private WebElement randomArticleLink;
    @FindBy(css = "#pt-userpage>a")
    private WebElement userPageLink;

    public WikiBaseMonoBookPageObject(WebDriver driver, String Domain) {
        super(driver);
        this.Domain = Domain;
        PageFactory.initElements(driver, this);
    }

    public void openWikiWithMonobook() {
        String baseUrl = Domain;
        getUrl(baseUrl + URLsContent.noexternals);
        PageObjectLogging.log(
            "openWikiBasePageError",
            "open wiki base page took more then 30 seconds",
            true
        );
        changeToMonoBook();
        PageObjectLogging.log(
            "changeBaseWikiSkin",
            "changing skin to monobook took more then 30 seconds",
            true, driver
        );
    }

    public void openRandomArticleMonobook() {
        waitForElementByElement(randomArticleLink);
        randomArticleLink.click();
    }

    public String addArticleByUrl(String articleName) {
        String url  = URLsContent.addArticle;
        articleName = articleName+getTimeStamp();
        url = url.replace("%title%", articleName);
        getUrl(Domain + url);
        PageObjectLogging.log(
            "addArticleUsingMonobook",
            "adding article by url",
            true, driver
        );
        return articleName;
    }

    public void openUserProfile() {
        waitForElementByElement(userPageLink);
        clickAndWait(userPageLink);
        changeToMonoBook();
        PageObjectLogging.log(
            "enterUserProfile",
            "enter user profile with monobook skin",
            true, driver
        );
    }
}
