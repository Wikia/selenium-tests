package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.io.FileUtils.readFileToString;

public class AdsRecoveryObject extends AdsBaseObject {
  private static final Dimension MEDREC_SIZE = new Dimension(300, 250);
  private static final Dimension TOP_LEADERBOARD_SIZE = new Dimension(728, 90);
  private static final int RECOVERABLE_ADS_COUNT = 4;
  private static final String EXPECTED_TOP_LEADERBOARD_PATH = "src/test/resources/adsResources/recovered_top_leaderboard";
  private static final String EXPECTED_MEDREC_PATH = "src/test/resources/adsResources/recovered_medrec";
  private static final By RECOVERABLE_SLOT_SELECTOR = By.cssSelector("[adonis-marker]");
  private static final By PF_RECOVERED_ADS_SELECTOR = By.cssSelector("body>span");

  public AdsRecoveryObject(WebDriver driver, String page, Dimension resolution) {
    super(driver, page, resolution);
  }

  public void verifyPageFairRecoveryWithAdBlock() {
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
    wait.forElementPresent(PF_RECOVERED_ADS_SELECTOR);

    // verify that adblock is turned on on that page
    verifyNoAdsOnPage();

    List<WebElement> recoveredAds = getRecoveredAds(PF_RECOVERED_ADS_SELECTOR);

    Assert.assertEquals(recoveredAds.size(), RECOVERABLE_ADS_COUNT);

    for (WebElement ad : recoveredAds) {
      Dimension adSize = ad.getSize();

      if (adSize.equals(TOP_LEADERBOARD_SIZE)) {
        Assertion.assertTrue(ad.getCssValue("background").contains(expectedRecoveredLB), "TOP_LEADERBOARD is not correctly recovered!");
      } else if (adSize.equals(MEDREC_SIZE)) {
        Assertion.assertTrue(ad.getCssValue("background").contains(expectedRecoveredMR), "MEDREC is not correctly recovered!");
      } else {
        Assertion.fail("Not supported PageFair recovery ad size encountered: " + adSize);
      }
    }
  }

  private List<WebElement> getRecoveredAds(By spansBodyChildrenSelector) {
    String firstSpanClass = driver.findElement(spansBodyChildrenSelector).getAttribute("class");
    return driver
        .findElements(By.cssSelector("body>span." + firstSpanClass))
        .stream()
        .filter(WebElement::isDisplayed)
        .filter(e -> e.getCssValue("background").contains("data:image/jpeg"))
        .collect(Collectors.toList());
  }

  public void verifyPageFairRecoveryWithNoAdBlock() {
    List<WebElement> markedSlots = driver.findElements(RECOVERABLE_SLOT_SELECTOR);
    Assertion.assertEquals(markedSlots.size(), RECOVERABLE_ADS_COUNT);
  }

  public void verifyPageFairRecoveryNoMarkersOnPage() {
    List<WebElement> markedSlots = driver.findElements(RECOVERABLE_SLOT_SELECTOR);
    Assertion.assertEquals(markedSlots.size(), 0);
  }
}
