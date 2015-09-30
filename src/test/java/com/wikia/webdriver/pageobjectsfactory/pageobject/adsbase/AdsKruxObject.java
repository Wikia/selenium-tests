package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.LOG;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @ownership AdEngineering
 */
public class AdsKruxObject extends AdsBaseObject {

  private static final String KRUX_CDN = "http://cdn.krxd.net/";
  private static final int MAX_SEGS_NUMBER_GPT = 27;
  private static final String KRUX_CONTROL_TAG_URL_PREFIX = KRUX_CDN + "controltag?confid=";
  private static final String PUB = "44c1a380-770f-11df-93f2-0800200c9a66";
  private static final String ADD_USER_URL =
      String.format("%suserdata/add?pub=%s&seg=", KRUX_CDN, PUB);
  @FindBy(css = "script[src^=\"" + KRUX_CONTROL_TAG_URL_PREFIX + "\"]")
  private WebElement kruxControlTag;

  public AdsKruxObject(WebDriver driver) {
    super(driver);
  }

  public AdsKruxObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
  }

  public void setKruxUserCookie(String userId) {
    driver.get(KRUX_CDN);
    setCookie("_kuid_", userId);
  }

  /**
   * Test whether the Krux control tag is called with the proper site ID
   *
   * @param kruxSiteId the expected Krux site ID
   */
  public void verifyKruxControlTag(String kruxSiteId) {
    String expectedUrl = KRUX_CONTROL_TAG_URL_PREFIX + kruxSiteId;
    Assertion.assertEquals(kruxControlTag.getAttribute("src"), expectedUrl);
  }

  /**
   * Test whether the Krux user id is not empty and added to GPT calls
   */
  public void verifyKruxUserParam(String slotName) {
    String script = "return localStorage.kxuser;";
    waitForKrux();
    String user1 = (String) ((JavascriptExecutor) driver).executeScript(script);
    refreshPage();
    waitForKrux();
    String user2 = (String) ((JavascriptExecutor) driver).executeScript(script);
    String gptPageParams = getGptPageParams(slotName);
    LOG.log("gpt page params", gptPageParams, LOG.Type.SUCCESS);
    LOG.logResult("krux users", user1 + ", " + user2, true);
    // TODO: figure out why we get krux user id in GPT calls from localStorage.kxuser in current PV OR from previous PV
    if (!gptPageParams.contains("u\":\"" + user1) && !gptPageParams.contains("u\":\"" + user2)) {
      throw new AssertionError("Gpt page params don't have the krux users from localStorage");
    }
  }

  public void waitForKrux() {
    LOG.log("waitForKrux", "Waiting for Krux", LOG.Type.SUCCESS);
    driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
    try {
      String script =
          "return !!localStorage.kxsegs || !!localStorage.kxkuid || !!localStorage.kxuser;";
      waitFor.until(CommonExpectedConditions.scriptReturnsTrue(script));
    } finally {
      restoreDeaultImplicitWait();
    }
  }

  public String getKxsegs() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String segments = (String) js.executeScript("return localStorage.kxsegs;");
    LOG.log("krux segments: ", segments, true, driver);
    return wrapSegs(segments);
  }

  private String wrapSegs(String segmentsLocalStorage) {
    if ("".equals(segmentsLocalStorage)) {
      return "[]";
    }
    String ksgmnt = "[";
    String[] segments = segmentsLocalStorage.split(",");
    if (segments.length > MAX_SEGS_NUMBER_GPT) {
      segments = Arrays.copyOfRange(segmentsLocalStorage.split(","), 0, MAX_SEGS_NUMBER_GPT);
    }
    for (String segment : segments) {
      ksgmnt += String.format("\"%s\",", segment);
    }
    ksgmnt = ksgmnt.substring(0, ksgmnt.length() - 1) + "]";
    return ksgmnt;
  }

  public void addSegmentToCurrentUser(String segmentId) {
    getUrl(ADD_USER_URL + segmentId);
    waitForPageLoaded();
    Assertion.assertTrue(driver.getPageSource().contains(segmentId));
  }
}
