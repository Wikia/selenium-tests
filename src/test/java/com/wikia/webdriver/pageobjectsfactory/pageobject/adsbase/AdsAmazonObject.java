package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsAmazonObject extends AdsBaseObject {

  private final static String AMAZON_SCRIPT_RESPONSE = "head > script[src*='amazon']";
  private final static String AMAZON_SCRIPT_URL = "amazon-adsystem.com/e/dtb/bid";
  private final static String AMAZON_SCRIPT = "script[src*=\"" + AMAZON_SCRIPT_URL + "\"]";

  @FindBy(css = "div[id*=_gpt][data-gpt-page-params*=amzn]")
  private List<WebElement> slotsWithAmazonParams;

  public AdsAmazonObject(
      WebDriver driver,
      String testedPage,
      NetworkTrafficInterceptor networkTrafficInterceptor
  ) {
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
      PageObjectLogging.log("RequestToAmazonIssued", "Request to amazon issued", true);
    } else {
      throw new NoSuchElementException("Request to Amazon not issued");
    }
  }

  public void verifyResponseFromAmazonPresent() {
    if (checkIfElementOnPage(AMAZON_SCRIPT_RESPONSE)) {
      PageObjectLogging.log("ScriptFromAmazonPresent", "Script returned by Amazon present", true);
    } else {
      throw new NoSuchElementException("Script from Amazon not found");
    }
  }

  public void verifyGPTParams() {
    if (slotsWithAmazonParams.size() > 0) {
      PageObjectLogging.log("AmazonAdFound", "Slot with amazon ad found", true);
    } else {
      throw new NoSuchElementException("Amazon Ad not found on page");
    }
  }
}
