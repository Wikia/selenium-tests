package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsAmazonObject extends AdsBaseObject {

  private final static String AMAZON_SCRIPT_URL = "amazon-adsystem.com/e/dtb";
  private final static String AMAZON_SCRIPT = "script[src*=\'" + AMAZON_SCRIPT_URL + "\']";
  private final static String AMAZON_IFRAME = "iframe[src*=\'" + AMAZON_SCRIPT_URL + "\']";
  private final static String AMAZON_GPT_PATTERN = "\"amznslots\":[\"a";

  @FindBy(css = "div[id*=_gpt][id*=TOP][data-gpt-slot-params*=amznslots], "
                + "div[id*=_gpt][id*=LEFT_SKYSCRAPER_2][data-gpt-slot-params*=amznslots]")
  private WebElement slotWithAmazon;

  public AdsAmazonObject(WebDriver driver, String testedPage,
                         NetworkTrafficInterceptor networkTrafficInterceptor) {
    super(driver, testedPage, networkTrafficInterceptor);
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

  public void verifyAdFromAmazonPresent() {
    WebElement amazonIframe = slotWithAmazon.findElement(
        By.cssSelector("div[id*=__container__] > iframe"));
    driver.switchTo().frame(amazonIframe);
    if (checkIfElementOnPage(AMAZON_IFRAME)) {
      PageObjectLogging.log("AmazonAd", "Script returned by Amazon present", true);
    } else {
      throw new NoSuchElementException("Amazon Ad not found on page");
    }
    driver.switchTo().defaultContent();
  }

  public void verifyGPTParams() {
    if (slotWithAmazon.getAttribute("data-gpt-slot-params").contains(AMAZON_GPT_PATTERN)) {
      PageObjectLogging.log("AmazonGptParams", "Slot with Amazon params found", true);
    } else {
      throw new NoSuchElementException("Amazon params not found on page");
    }
  }
}
