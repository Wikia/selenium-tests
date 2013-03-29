package com.wikia.webdriver.PageObjectsFactory.PageObject.CrossWikiSearch;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Author: Artur Dwornik
 * Date: 28.03.13
 * Time: 19:29
 */
public class CrossWikiSearchPage extends BasePageObject {

    @FindBy(css="#search-v2-input")
    protected WebElement searchInput;
    @FindBy(css="#search-v2-button")
    protected WebElement searchButton;
    @FindBy(css=".Results > :nth-child(1)")
    protected WebElement firstResult;
    @FindBy(css=".Results > :nth-child(1) h1 > a")
    protected WebElement firstResultLink;
    @FindBy(css=".Results > :nth-child(1) > .result-description > :nth-child(2)")
    protected WebElement firstResultVertical;
    @FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle")
    protected WebElement firstResultStatistics;
    @FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(1)")
    protected WebElement firstResultStatisticsPageCount;
    @FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(2)")
    protected WebElement firstResultStatisticsPageImages;
    @FindBy(css=".Results > :nth-child(1) .wiki-statistics.subtle > :nth-child(3)")
    protected WebElement firstResultStatisticsPageVideos;


    public CrossWikiSearchPage(WebDriver driver) {
        super(driver);
    }


    public void verifyFirstResultTitle(String wikiName) {
        waitForTextToBePresentInElementByElement(firstResultLink, wikiName);
    }

    public void verifyFirstResultVertical(String vertical) {
        waitForTextToBePresentInElementByElement(firstResultVertical, vertical);
    }

    public void verifyFirstResultDescription() {
        waitForElementByElement(firstResultVertical);
    }

    public void verifyFirstResultPageCount() {
        waitForElementByElement(firstResultStatisticsPageCount);
    }

    public void verifyFirstResultPageImages() {
        waitForElementByElement(firstResultStatisticsPageImages);
    }

    public void verifyFirstResultPageVideos() {
        waitForElementByElement(firstResultStatisticsPageVideos);
    }
}
