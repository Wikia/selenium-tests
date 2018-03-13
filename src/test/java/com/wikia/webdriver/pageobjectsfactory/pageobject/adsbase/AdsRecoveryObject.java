package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import static org.apache.commons.io.FileUtils.readFileToString;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class AdsRecoveryObject extends AdsBaseObject {
  private static final Dimension MEDREC_SIZE = new Dimension(300, 250);
  private static final Dimension LEADERBOARD_SIZE = new Dimension(728, 90);
  private static final String EXPECTED_LEADERBOARD_PATH = "src/test/resources/adsResources/recovered_top_leaderboard";
  private static final String EXPECTED_MEDREC_PATH = "src/test/resources/adsResources/recovered_medrec";
  private static final By RECOVERABLE_SLOT_SELECTOR = By.cssSelector("[adonis-marker]");
  public static final By PF_RECOVERED_ADS_SELECTOR = By.cssSelector("body>div");

  public AdsRecoveryObject(WebDriver driver, String page, Dimension resolution) {
    super(driver, page, resolution);
  }

  public void assertIfAllRecoveredSlotHasCorrectSizeAndBackground(List<WebElement> recoveredAds) {
    String expectedRecoveredLB;
    String expectedRecoveredMR;
    try {
      expectedRecoveredLB = readFileToString(new File(EXPECTED_LEADERBOARD_PATH));
      expectedRecoveredMR = readFileToString(new File(EXPECTED_MEDREC_PATH));
    } catch (IOException e) {
      PageObjectLogging.log("Can't open expected PageFair recovery file.", e, false);
      throw new WebDriverException("Can't open expected PageFair recovery file.");
    }

    for (WebElement ad : recoveredAds) {
      Dimension adSize = ad.getSize();

      if (adSize.equals(LEADERBOARD_SIZE)) {
        Assertion.assertTrue(ad.getCssValue("background").contains(expectedRecoveredLB), "TOP_LEADERBOARD is not correctly recovered!");
      } else if (adSize.equals(MEDREC_SIZE)) {
        Assertion.assertTrue(ad.getCssValue("background").contains(expectedRecoveredMR), "MEDREC is not correctly recovered!");
      } else {
        Assertion.fail("Not supported PageFair recovery ad size encountered: " + adSize);
      }
    }
  }

  public List<WebElement> getRecoveredAds(By elementsBodyChildrenSelector) {
    String firstElmentClass = driver.findElement(elementsBodyChildrenSelector).getAttribute("class");
    return driver
        .findElements(By.cssSelector("body>div." + firstElmentClass))
        .stream()
        .filter(WebElement::isDisplayed)
        .filter(e -> e.getCssValue("background").contains("data:image/jpeg"))
        .collect(Collectors.toList());
  }

  public void verifyNumberOfAdonisMarkedSlots(int expected) {
    waitForPageLoaded();
    List<WebElement> markedSlots = driver.findElements(RECOVERABLE_SLOT_SELECTOR);
    Assertion.assertEquals(markedSlots.size(), expected);
  }
}
