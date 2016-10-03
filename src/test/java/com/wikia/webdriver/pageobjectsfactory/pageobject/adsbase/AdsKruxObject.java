package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.CommonExpectedConditions;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class AdsKruxObject extends AdsBaseObject {

  private static final String KRUX_CDN = "http://cdn.krxd.net/";
  private static final String KRUX_CONTROL_TAG_URL_PREFIX = KRUX_CDN + "controltag?confid=";
  private static final String PUB = "44c1a380-770f-11df-93f2-0800200c9a66";

  @FindBy(css = "script[src^=\"" + KRUX_CONTROL_TAG_URL_PREFIX + "\"]")
  private WebElement kruxControlTag;

  public AdsKruxObject(WebDriver driver, String testedPage) {
    super(driver, testedPage);
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
    String user1 = (String) ((JavascriptExecutor) driver).executeScript(script);

    // Fourth page view
    refreshPage();

    String user2 = (String) ((JavascriptExecutor) driver).executeScript(script);
    String gptPageParams = getGptPageParams(slotName);

    PageObjectLogging.log("gpt page params", gptPageParams, true);
    PageObjectLogging.log("krux users", user1 + ", " + user2, true);

    // TODO: figure out why we get krux user id in GPT calls from localStorage.kxuser in current PV OR from previous PV
    if (!gptPageParams.contains("u\":\"" + user1) && !gptPageParams.contains("u\":\"" + user2)) {
      throw new AssertionError("Gpt page params don't have the krux users from localStorage");
    }
  }
}
