package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.concurrent.TimeUnit;

public class AdsAmazonObject extends AdsBaseObject {

  private static final String AMAZON_SCRIPT_URL = "amazon-adsystem.com/e/dtb";
  private static final String AMAZON_BID_URL = "aax.amazon-adsystem.com/e/dtb/bid";
  private static final String AMAZON_SCRIPT = "script[src*=\'" + AMAZON_SCRIPT_URL + "\']";
  private static final String AMAZON_IFRAME = "iframe[src*=\'" + AMAZON_SCRIPT_URL + "\']";
  private static final String AMAZON_GPT_PATTERN = "\"amznslots\":[\"a";
  private static final String AMAZON_SLOTS_CSS_SELECTOR =
      "div[id*=_gpt][data-gpt-slot-params*=amznslots]:not(.hidden)";

  private static final ImmutableMap<String, String> amazonLinkCssSelectors =
      new ImmutableMap.Builder<String, String>()
          .put("AmazonSecondPageView", "a[href='/wiki/SyntheticTests/Amazon/2']")
          .put("AmazonThirdPageView", "a[href='/wiki/SyntheticTests/Amazon/3']")
          .build();

  private static final ImmutableMap<String, String> amazonLinkTitles =
      new ImmutableMap.Builder<String, String>()
          .put("AmazonSecondPageView", "SyntheticTests/Amazon/2")
          .put("AmazonThirdPageView", "SyntheticTests/Amazon/3")
          .build();

  @FindBy(css = AMAZON_SLOTS_CSS_SELECTOR)
  private WebElement slotWithAmazon;

  public AdsAmazonObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  private WebElement getAmazonIframe(WebElement slotWithAmazon) {
    wait.forElementVisible(slotWithAmazon);
    return slotWithAmazon.findElement(By.cssSelector(
        "div[id*=__container__] > iframe"
    ));
  }

  private void waitForAmazonResponse() {
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);

    try {
      waitFor.until(new ExpectedCondition<Boolean>() {
        public Boolean apply(WebDriver driver) {
          return (Boolean) ((JavascriptExecutor) driver)
              .executeAsyncScript(
                  "var callback = arguments[0];" +
                  "require(['ext.wikia.adEngine.lookup.amazonMatch'], function (amazon) {\n" +
                  "   callback(amazon.hasResponse());\n" +
                  "});"
              );
        }
      });
    } finally {
      restoreDefaultImplicitWait();
    }
  }

  public void verifyAmazonScriptIncluded() {
    if (isElementOnPage(By.cssSelector(AMAZON_SCRIPT))) {
      PageObjectLogging.log("AmazonScriptFound", "Script from Amazon found", true);
    } else {
      throw new NoSuchElementException("Amazon script not found on page");
    }
  }

  public AdsAmazonObject verifyAdsFromAmazonPresent() {
    driver.switchTo().frame(getAmazonIframe(slotWithAmazon));
    Assertion.assertTrue(isElementOnPage(By.cssSelector(AMAZON_IFRAME)));
    PageObjectLogging.log("AmazonAd", "Script returned by Amazon present", true);
    driver.switchTo().defaultContent();
    return this;
  }

  public AdsAmazonObject verifyNoAdsFromAmazonPresent() {
    Assertion.assertFalse(isElementOnPage(By.cssSelector(AMAZON_SLOTS_CSS_SELECTOR)));
    PageObjectLogging.log("AmazonAd", "No Amazon ad present", true);
    return this;
  }

  public void verifyGPTParams() {
    if (slotWithAmazon.getAttribute("data-gpt-slot-params").contains(AMAZON_GPT_PATTERN)) {
      PageObjectLogging.log("AmazonGptParams", "Slot with Amazon params found", true);
    } else {
      throw new NoSuchElementException("Amazon params not found on page");
    }
  }

  public void verifyResponseIsValid(NetworkTrafficInterceptor networkTab) {
    wait.forSuccessfulResponse(networkTab, AMAZON_BID_URL);
  }

  public AdsAmazonObject clickAmazonArticleLink(String linkName) {
    waitForAmazonResponse();
    WebElement amazonArticleLink = driver.findElement(
        By.cssSelector(amazonLinkCssSelectors.get(linkName))
    );
    wait.forElementVisible(amazonArticleLink);
    amazonArticleLink.click();
    waitTitleChangesTo(amazonLinkTitles.get(linkName));
    return this;
  }
}
