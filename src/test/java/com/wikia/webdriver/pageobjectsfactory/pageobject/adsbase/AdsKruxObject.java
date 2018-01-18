package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdsKruxObject extends AdsBaseObject {

  private final String kruxCdn = urlBuilder.getUrlProtocol() + "cdn.krxd.net/";
  private final String kruxControlTagUrlPrefix = kruxCdn + "controltag?confid=";

  @FindBy(css = "script[src*='cdn.krxd.net/controltag?confid=']")
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
    String expectedUrl = kruxControlTagUrlPrefix + kruxSiteId;
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
