package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.TestContext;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ContentReviewModule;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * Created by Ludwik on 2015-09-25.
 */
public class SpecialJsPage extends WikiBasePageObject {

    @FindBy(css = ".source-javascript")
    private WebElement scriptArea;
    @FindBy(css = "#content-review-selenium-test-element")
    private WebElement testElement;
    @FindBy(css = "a.content-review-module-test-mode-disable")
    private WebElement bannerNotificationLink;

    private ContentReviewModule contentReviewModule;

    public SpecialJsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Open article with name that is the following combination: TEST CLASS NAME + TEST METHOD NAME
     */
    public SpecialJsPage open() {
        getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
                + String.format("mediawiki:%s.js", TestContext.getCurrentMethodName()));

        return this;
    }

    public SpecialJsPage open(String articleTitle) {
        getUrl(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + URLsContent.WIKI_DIR
                + String.format("mediawiki:%s.js", articleTitle));
        return this;
    }

    public String getScriptContent() {
        wait.forElementVisible(scriptArea);

        return scriptArea.getText();
    }

    public ContentReviewModule getReviewModule() {
        if (contentReviewModule == null) {
            contentReviewModule = new ContentReviewModule(driver);
        }
        return contentReviewModule;
    }

    public boolean isTestElementVisible() {
        boolean isVisible = false;
        try {
            wait.forElementVisible(testElement, 3, 1);
            isVisible = true;
        } catch (TimeoutException $e) {
            isVisible = false;
        }
        return isVisible;
    }

    public String getTestElementContent() {
        wait.forElementVisible(testElement, 3, 1);
        return testElement.getText();
    }

    public boolean isBannerNotificationLinkVisible() {
        boolean isVisible = false;
        try {
            wait.forElementVisible(bannerNotificationLink, 3, 1);
            isVisible = true;
        } catch (TimeoutException $e) {
            isVisible = false;
        }
        return isVisible;
    }
}
