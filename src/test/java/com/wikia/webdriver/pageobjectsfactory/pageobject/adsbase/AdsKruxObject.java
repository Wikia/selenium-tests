package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Dmytro Rets
 * @ownership AdEngineering
 */
public class AdsKruxObject extends AdsBaseObject {

  private static final String KRUX_CDN = "http://cdn.krxd.net/";
  private static final int MAX_SEGS_NUMBER_GPT = 27;
  private static final String SLOT_SELECTOR = "div[id*='wikia_gpt_helper/5441']";
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
    String script = "return localStorage.kxuser;";
    waitForKrux();
    String user1 = (String) ((JavascriptExecutor) driver).executeScript(script);
    refreshPage();
    waitForKrux();
    String user2 = (String) ((JavascriptExecutor) driver).executeScript(script);
    // TODO: figure out why we get krux user id in GPT calls from localStorage.kxuser in current PV OR from previous PV
    Assertion.assertTrue(isGptParamPresent(SLOT_SELECTOR, "u", user1) ||
                         isGptParamPresent(SLOT_SELECTOR, "u", user2));
  }

  public void waitForKrux() {
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    try {
      String script =
          "return !!localStorage.kxsegs || !!localStorage.kxkuid || !!localStorage.kxuser;";
      wait.until(CommonExpectedConditions.scriptReturnsTrue(script));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public void setKruxUserCookie(String userId) {
    getUrl(KRUX_CDN);
    waitPageLoaded();
    setCookie("_kuid_", userId);
  }

  public String getKxsegs() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String segments = (String) js.executeScript("return localStorage.kxsegs;");
    PageObjectLogging.log("krux segments: ", segments, true, driver);
    return segments;
  }

  public String getKxkuid() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String kxkuid = (String) js.executeScript("return localStorage.kxkuid;");
    PageObjectLogging.log("krux kuid: ", kxkuid, true, driver);
    return kxkuid;
  }

  public String getKsgmntPattern(String segmentsLocalStorage) {
    String ksgmnt = "\"ksgmnt\":[";
    List<String> segments = sliceSegsForGpt(segmentsLocalStorage);
    for (String segment : segments) {
      ksgmnt += String.format("\"%s\",", segment);
    }
    ksgmnt = ksgmnt.substring(0, ksgmnt.length() - 1) + "]";
    return ksgmnt;
  }

  /**
   * Slice the krux segments to MAX_SEGS_NUMBER_GPT number for gpt call and set highest priority to
   * special segment
   *
   * @param segments Krux segments
   */
  private List<String> sliceSegsForGpt(String segments) {
    String specialSegment = "ph3uhzc41";
    String[] segs = segments.split(",");
    if (segs.length > MAX_SEGS_NUMBER_GPT) {
      List<String> slicedSegs = Arrays.asList(segs).subList(0, MAX_SEGS_NUMBER_GPT);
      if (segments.contains(specialSegment) && !slicedSegs.contains(specialSegment)) {
        slicedSegs = new LinkedList<>(Arrays.asList(segs).subList(0, MAX_SEGS_NUMBER_GPT - 1));
        slicedSegs.add(0, specialSegment);
      }
      return slicedSegs;
    }
    return Arrays.asList(segs);
  }

}
