package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.security.InvalidParameterException;


/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsAmazonObject extends AdsBaseObject {

    private final static String AMAZON_SCRIPT_URL = "amazon-adsystem.com/e/dtb";
    private final static String AMAZON_SCRIPT = "script[src*=\'" + AMAZON_SCRIPT_URL + "\']";
    private final static String AMAZON_IFRAME = "iframe[src*=\'" + AMAZON_SCRIPT_URL + "\']";
    private final static String AMAZON_GPT_PATTERN = "\"amznslots\":[\"a";

    private final static String AMAZON_ARTICLE_LINK_CSS = "a[href='/wiki/Amazon']";
    private final static String AMAZON_SECOND_ARTICLE_LINK_CSS =
            "a[href='wiki/SyntheticTests/AmazonStep2']";

    @FindBy(css = "div[id*=_gpt][data-gpt-slot-params*=amznslots]")
    private WebElement slotWithAmazon;

    private WebElement getAmazonIframe(WebElement slotWithAmazon) {
        waitForElementByElement(slotWithAmazon);
        return slotWithAmazon.findElement(By.cssSelector(
                "div[id*=__container__] > iframe"
        ));
    }

    public AdsAmazonObject(WebDriver driver, String testedPage) {
        super(driver, testedPage);
    }

    public void verifyAmazonScriptIncluded() {
        if (checkIfElementOnPage(AMAZON_SCRIPT)) {
            PageObjectLogging.log("AmazonScriptFound", "Script from Amazon found", true);
        } else {
            throw new NoSuchElementException("Amazon script not found on page");
        }
    }

    public void verifyCallToAmazonIssued() {
        if (networkTrafficInterceptor.searchRequestUrlInHar(AMAZON_SCRIPT_URL)) {
            PageObjectLogging.log("RequestToAmazonIssued", "Request to Amazon issued", true);
        } else {
            throw new NoSuchElementException("Request to Amazon not issued");
        }
    }

    public AdsAmazonObject verifyAdsFromAmazonPresent() {
        driver.switchTo().frame(getAmazonIframe(slotWithAmazon));
        Assertion.assertTrue(checkIfElementOnPage(AMAZON_IFRAME));
        PageObjectLogging.log("AmazonAd", "Script returned by Amazon present", true);
        driver.switchTo().defaultContent();
        return this;
    }

    public AdsAmazonObject verifyNoAdsFromAmazonPresent() {
        driver.switchTo().frame(getAmazonIframe(slotWithAmazon));
        Assertion.assertFalse(checkIfElementOnPage(AMAZON_IFRAME));
        PageObjectLogging.log("AmazonAd", "No Amazon ad present", true);
        driver.switchTo().defaultContent();
        return this;
    }

    public void verifyGPTParams() {
        if (slotWithAmazon.getAttribute("data-gpt-slot-params").contains(AMAZON_GPT_PATTERN)) {
            PageObjectLogging.log("AmazonGptParams", "Slot with Amazon params found", true);
        } else {
            throw new NoSuchElementException("Amazon params not found on page");
        }
    }

    public AdsAmazonObject clickAmazonArticleLink(String linkSelectoryInCss) {
        WebElement amazonArticleLink = driver.findElement(By.cssSelector(linkSelectoryInCss));
        waitForElementByElement(amazonArticleLink);
        amazonArticleLink.click();
        return this;
    }

    public String getAmazonLinkCssSelector(String linkName) {
        if (linkName.equals("AmazonArticle")) {
            return AMAZON_ARTICLE_LINK_CSS;
        } else if (linkName.equals("AmazonSecondArticle")) {
            return AMAZON_SECOND_ARTICLE_LINK_CSS;
        }

        throw new InvalidParameterException("Invalid linkName parameter");
    }
}
