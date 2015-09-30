package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.google.common.collect.ImmutableMap;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.LOG;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsAmazonObject extends AdsBaseObject {

  private static final String AMAZON_SCRIPT_URL = "amazon-adsystem.com/e/dtb";
  private static final String AMAZON_SCRIPT = "script[src*=\'" + AMAZON_SCRIPT_URL + "\']";
  private static final String AMAZON_IFRAME = "iframe[src*=\'" + AMAZON_SCRIPT_URL + "\']";
  private static final String AMAZON_GPT_PATTERN = "\"amznslots\":[\"a";
  private static final String AMAZON_SLOTS_CSS_SELECTOR =
      "div[id*=_gpt][data-gpt-slot-params*=amznslots]:not(.hidden)";

  private static final ImmutableMap<String, String> amazonLinkCssSelectors =
      new ImmutableMap.Builder<String, String>()
          .put("AmazonFirstArticle", "a[href='/wiki/Amazon']")
          .put("AmazonSecondArticle", "a[href='/wiki/SyntheticTests/AmazonStep2']").build();

  private static final ImmutableMap<String, String> amazonLinkTitles =
      new ImmutableMap.Builder<String, String>().put("AmazonFirstArticle", "Amazon")
          .put("AmazonSecondArticle", "SyntheticTests/AmazonStep2").build();

  @FindBy(css = AMAZON_SLOTS_CSS_SELECTOR)
  private WebElement slotWithAmazon;

  public AdsAmazonObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  private WebElement getAmazonIframe(WebElement slotWithAmazon) {
    wait.forElementVisible(slotWithAmazon);
    return slotWithAmazon.findElement(By.cssSelector("div[id*=__container__] > iframe"));
  }

  private void waitForAmazonResponse() {
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

    try {
      waitFor.until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver driver) {
          return (Boolean) ((JavascriptExecutor) driver)
              .executeAsyncScript("var callback = arguments[0];"
                  + "require(['ext.wikia.adEngine.lookup.amazonMatch'], function (amazon) {\n"
                  + "   callback(amazon.hasResponse());\n" + "});");
        }
      });
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void verifyAmazonScriptIncluded() {
    if (isElementOnPage(By.cssSelector(AMAZON_SCRIPT))) {
      LOG.success("AmazonScriptFound", "Script from Amazon found");
    } else {
      throw new NoSuchElementException("Amazon script not found on page");
    }
  }

  public AdsAmazonObject verifyAdsFromAmazonPresent() {
    driver.switchTo().frame(getAmazonIframe(slotWithAmazon));
    Assertion.assertTrue(isElementOnPage(By.cssSelector(AMAZON_IFRAME)));
    LOG.success("AmazonAd", "Script returned by Amazon present");
    driver.switchTo().defaultContent();
    return this;
  }

  public AdsAmazonObject verifyNoAdsFromAmazonPresent() {
    Assertion.assertFalse(isElementOnPage(By.cssSelector(AMAZON_SLOTS_CSS_SELECTOR)));
    LOG.success("AmazonAd", "No Amazon ad present");
    return this;
  }

  public void verifyGPTParams() {
    if (slotWithAmazon.getAttribute("data-gpt-slot-params").contains(AMAZON_GPT_PATTERN)) {
      LOG.success("AmazonGptParams", "Slot with Amazon params found");
    } else {
      throw new NoSuchElementException("Amazon params not found on page");
    }
  }

  public AdsAmazonObject clickAmazonArticleLink(String linkName) {
    waitForAmazonResponse();
    WebElement amazonArticleLink =
        driver.findElement(By.cssSelector(amazonLinkCssSelectors.get(linkName)));
    wait.forElementVisible(amazonArticleLink);
    amazonArticleLink.click();
    waitTitleChangesTo(amazonLinkTitles.get(linkName));
    return this;
  }
}
