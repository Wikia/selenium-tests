package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.google.common.base.Joiner;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsKruxObject extends AdsBaseObject {

  private static final String KRUX_CDN = "http://cdn.krxd.net/";
  private static final String KRUX_CONTROL_TAG_URL_PREFIX = KRUX_CDN + "controltag?confid=";
  @FindBy(css = "script[src^=\"" + KRUX_CONTROL_TAG_URL_PREFIX + "\"]")
  private WebElement kruxControlTag;

  public AdsKruxObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public AdsKruxObject(WebDriver driver) {
    super(driver);
  }

  /**
   * Test whether the Krux control tag is called with the proper site ID
   *
   * @param kruxSiteId the expected Krux site ID
   */
  public void verifyKruxControlTag(String kruxSiteId) {
    String expectedUrl = KRUX_CONTROL_TAG_URL_PREFIX + kruxSiteId;
    Assertion.assertEquals(expectedUrl, kruxControlTag.getAttribute("src"));
  }

  /**
   * Test whether the Krux user id is not empty and added to GPT calls
   */
  public void verifyKruxUserParam() {
    waitForKrux();
    String kruxUser = (String) ((JavascriptExecutor) driver).executeScript("return Krux.user;");
    Assertion.assertStringNotEmpty(kruxUser);
    WebElement iframe = driver.findElement(By.cssSelector("div[id*='wikia_gpt_helper/5441']"));
    Assertion.assertTrue(isGptParamPresent(iframe, "u", kruxUser));
  }

  public void waitForKrux() {
    waitPageLoaded();
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    try {
      wait.until(CommonExpectedConditions.scriptReturnsTrue("return !!window.Krux"));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void setKruxUserCookie(String userId) {
    getUrl(KRUX_CDN);
    waitPageLoaded();
    setCookie("_kuid_", userId);
  }

  public String getKruxSegments() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    List segments = (ArrayList) js.executeScript("return Krux.segments;");
    PageObjectLogging.log("getKruxSegments", segments.toString(), true);
    return Joiner.on("\t").join(segments);
  }

  public String getKxsegs() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return (String) js.executeScript("return localStorage.kxsegs;");
  }

  public String getKxkuid() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    return (String) js.executeScript("return localStorage.kxkuid;");
  }

}
