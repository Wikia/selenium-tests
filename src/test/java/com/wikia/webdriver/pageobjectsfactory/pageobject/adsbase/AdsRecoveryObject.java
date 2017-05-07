package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import static org.apache.commons.io.FileUtils.readFileToString;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AdsRecoveryObject extends AdsBaseObject {
  private static final String EXPECTED_TOP_LEADERBOARD_PATH = "src/test/resources/adsResources/recovered_top_leaderboard";
  private static final String EXPECTED_MEDREC_PATH = "src/test/resources/adsResources/recovered_medrec";

  public AdsRecoveryObject(WebDriver driver, String page, Dimension resolution) {
    super(driver, page, resolution);
  }

  public String getRecoveredAdUnitId(String adUnitId) {
    String getIdScript = String.format("window._sp_.getElementId('%s')", adUnitId);
    jsActions.waitForJavaScriptTruthy(String.format("window._sp_ && window._sp_.getElementId && %s", getIdScript));
    return jsActions.execute(getIdScript).toString();
  }

  public void verifyPageFairRecoveryWithAdBlock() {
    int recoverableAdsCount = 2;
    By spansBodyChildrenSelector = By.cssSelector("body>span");
    Dimension topLeaderboardSize = new Dimension(728, 90);
    Dimension medrecSize = new Dimension(300, 250);
    String expectedRecoveredLB;
    String expectedRecoveredMR;

    try {
      expectedRecoveredLB = readFileToString(new File(EXPECTED_TOP_LEADERBOARD_PATH));
      expectedRecoveredMR = readFileToString(new File(EXPECTED_MEDREC_PATH));
    } catch (IOException e) {
      PageObjectLogging.log("Can't open expected PageFair recovery file.", e, false);
      throw new WebDriverException("Can't open expected PageFair recovery file.");
    }

    // when PF recovered ad is on page, inserts span elements as a direct children of body
    wait.forElementPresent(spansBodyChildrenSelector);

    // verify that adblock is turned on on that page
    verifyNoAdsOnPage();

    String firstSpanClass = driver.findElement(spansBodyChildrenSelector).getAttribute("class");
    List<WebElement> recoveredAds = driver
        .findElements(By.cssSelector("body>span." + firstSpanClass))
        .stream()
        .filter(WebElement::isDisplayed)
        .filter(e -> e.getCssValue("background").contains("data:image/jpeg"))
        .collect(Collectors.toList());

    Assert.assertEquals(recoveredAds.size(), recoverableAdsCount);

    for (WebElement ad : recoveredAds) {
      Dimension adSize = ad.getSize();

      if (adSize.equals(topLeaderboardSize)) {
        Assertion.assertTrue(ad.getCssValue("background").contains(expectedRecoveredLB), "TOP_LEADERBOARD is not correctly recovered!");
      } else if (adSize.equals(medrecSize)) {
        Assertion.assertTrue(ad.getCssValue("background").contains(expectedRecoveredMR), "MEDREC is not correctly recovered!");
      } else {
        Assertion.fail("Not supported PageFair recovery ad size encountered: " + adSize);
      }
    }
  }

  public void verifyPageFairRecoveryWithNoAdBlock() {
    List<WebElement> markedSlots = driver.findElements(By.cssSelector("[adonis-marker]"));
    Assertion.assertEquals(markedSlots.size(), 0);
  }

  public void waitForRecoveredSlot(String slotName) {
    // We are waitnig for Source Point recovered slot (second div appears in slot)
    wait.forElementPresent(By.cssSelector("#" + slotName + " .provider-container > div:nth-child(2)"));
  }
}
