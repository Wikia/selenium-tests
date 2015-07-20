package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
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
  private static final int MAX_PAGE_REFRESHES = 5;
  private static final String PUB = "44c1a380-770f-11df-93f2-0800200c9a66";
  private static final String ADD_USER_URL =
      String.format("%suserdata/add?pub=%s&seg=", KRUX_CDN, PUB);
  private final static ImmutableMap<String, String> kruxSegs =
      new ImmutableMap.Builder<String, String>()
          .put("pqdapsy7l", "J6IRfe2v")
          .build();
  @FindBy(css = "script[src^=\"" + KRUX_CONTROL_TAG_URL_PREFIX + "\"]")
  private WebElement kruxControlTag;

  public AdsKruxObject(WebDriver driver, String page) {
    super(driver, page);
  }

  public AdsKruxObject(WebDriver driver) {
    super(driver);
  }

  @Override
  public void getUrl(String url) {
    getUrl(url, false);
    waitForKrux();
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

  public String getKxsegs() {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String segments = (String) js.executeScript("return localStorage.kxsegs;");
    PageObjectLogging.log("krux segments: ", segments, true, driver);
    return wrapSegs(segments);
  }

  private String wrapSegs(String segmentsLocalStorage) {
    String ksgmnt = "\"ksgmnt\":[";
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

  public void initKruxUser(String wikiName) {
    int i = 0;
    while (i < MAX_PAGE_REFRESHES) {
      getUrl(urlBuilder.getUrlForWiki(wikiName), false);
      waitForPageLoaded();
      try {
        new WebDriverWait(driver, 5).until(
            CommonExpectedConditions.scriptReturnsTrue("return !!localStorage.kxuser;"));
        break;
      } catch (org.openqa.selenium.TimeoutException ignore) {
        i++;
      }
    }
  }

  public void addSegmentToCurrentUser(String segmentId, String wikiName) {
    int i = 0;
    while (i < MAX_PAGE_REFRESHES) {
      getUrl(ADD_USER_URL + kruxSegs.get(segmentId), false);
      waitForPageLoaded();
      Assertion.assertTrue(driver.getPageSource().contains(segmentId));
      getUrl(urlBuilder.getUrlForWiki(wikiName), false);
      waitForPageLoaded();
      try {
        new WebDriverWait(driver, 5).until(CommonExpectedConditions.scriptReturnsTrue(
            "return localStorage.kxsegs.includes(arguments[0]);", segmentId));
        break;
      } catch (org.openqa.selenium.TimeoutException ignore) {
        i++;
      }
    }
  }
}
