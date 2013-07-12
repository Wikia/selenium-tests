/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wikia.webdriver.PageObjectsFactory.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.URLsContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class WikiBaseMonoBookPageObject extends BaseMonoBookPageObject {

    @FindBy(css = "#n-randompage a")
    private WebElement randomArticleLink;
    @FindBy(css = "#pt-userpage>a")
    private WebElement userPageLink;
    @FindBy(css = "#pt-userpage>a")
    private WebElement closeDisclaimer;

    public WikiBaseMonoBookPageObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openWikiWithMonobook() {
        getUrl(Global.DOMAIN + URLsContent.noexternals);
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
        getUrl(Global.DOMAIN + url);
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
