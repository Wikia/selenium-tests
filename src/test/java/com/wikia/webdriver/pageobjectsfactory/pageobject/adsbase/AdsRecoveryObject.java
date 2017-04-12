package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class AdsRecoveryObject extends AdsBaseObject {

  public AdsRecoveryObject(WebDriver driver, String page, Dimension resolution) {
    super(driver, page, resolution);
  }

  public String getRecoveredAdUnitId(String adUnitId) {
    String getIdScript = String.format("window._sp_.getElementId('%s')", adUnitId);
    jsActions.waitForJavaScriptTruthy(String.format("window._sp_ && window._sp_.getElementId && %s", getIdScript));
    return jsActions.execute(getIdScript).toString();
  }

  public void verifyPageFairRecovery() {
    By spansBodyChildrenSelector = By.cssSelector("body>span");
    Dimension topLeaderboardSize = new Dimension(728, 90);
    Dimension medrecSize = new Dimension(300, 250);

    // when PF recovered ad is on page, inserts span elements as a direct children of body
    wait.forElementPresent(spansBodyChildrenSelector);

    // verify that adblock is turned on on that page
    verifyNoAdsOnPage();

    String firstSpanClass = driver.findElement(spansBodyChildrenSelector).getAttribute("class");
    //System.out.println("\n\nCLASS:" + firstSpanClass);
    List<WebElement> recoveredAds = driver
        .findElements(By.cssSelector("body>span." + firstSpanClass))
        .stream()
        .filter(WebElement::isDisplayed)
        .filter(e -> e.getCssValue("background").contains("data:image/jpeg"))
        .collect(Collectors.toList());

    String recoveredTopLeaderboard = "data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAABLAAD/4QPZaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29";
    String recoveredMedrec = "data:image/jpeg;base64,/9j/4QAYRXhpZgAASUkqAAgAAAAAAAAAAAAAAP/sABFEdWNreQABAAQAAAA3AAD/4QPZaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLwA8P3hwYWNrZXQgYmVnaW49Iu+7vyIgaWQ9Ilc1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCI/PiA8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJBZG9iZSBYTVAgQ29";

    for (WebElement ad : recoveredAds) {
      System.out.println("\n\nNA STRONIE:");
      System.out.println(ad.getAttribute("class"));
      System.out.println(ad.getSize());
      System.out.println(ad.getCssValue("background").substring(0, 300));

      if (ad.getSize() == topLeaderboardSize) {
        Assertion.assertTrue(ad.getCssValue("background").contains(recoveredTopLeaderboard), "Ad of size" + ad.getSize() + "is not recovered!");
      } else if (ad.getSize() == medrecSize) {
        Assertion.assertTrue(ad.getCssValue("background").contains(recoveredMedrec), "Ad of size" + ad.getSize() + "is not recovered!");
      }
    }
  }
}
