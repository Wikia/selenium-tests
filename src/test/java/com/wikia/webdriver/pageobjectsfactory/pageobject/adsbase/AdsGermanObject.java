package com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.helpers.AdsComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bogna 'bognix' Knychala
 */
public class AdsGermanObject extends AdsBaseObject {

  private static final String JS_SKIN_CALL = "top.loadCustomAd({type:\"skin\",destUrl:\"";
  /*
   * List of all possible combinations for 71M ads with their characteristic slots
   */
  private List<Map<String, Object>> combinations = new ArrayList<>();

  public AdsGermanObject(WebDriver driver, String page) {
    super(driver);
    getUrl(page);
    setSlots();
    driver.manage().window().setSize(new Dimension(1920, 1080));
  }

  private void setSlots() {
    Map<String, Object> billboardMap = new HashMap<String, Object>();
    Map<String, Object> fireplaceMap = new HashMap<String, Object>();
    Map<String, Object> flashtalkingMap = new HashMap<String, Object>();
    Map<String, Object> wpInternMap = new HashMap<String, Object>();
    Map<String, Object> leaderboardMap = new HashMap<String, Object>();
    Map<String, Object> medrecMap = new HashMap<String, Object>();
    Map<String, Object> prefooterMap = new HashMap<String, Object>();

    List<String> billboard = new ArrayList<String>();
    List<String> fireplace = new ArrayList<String>();
    List<String> flashtalking = new ArrayList<String>();
    List<String> wpIntern = new ArrayList<String>();
    List<String> leaderboard = new ArrayList<String>();
    List<String> medrec = new ArrayList<String>();
    List<String> prefooter = new ArrayList<String>();

    billboard.add("#ad-skyscraper1-outer");
    billboardMap.put("name", "billboard");
    billboardMap.put("slots", billboard);

    fireplace.add("#soi_wp_skyscraper2_outer");
    fireplace.add("#soi_wp_skyscraper1_outer");
    fireplaceMap.put("name", "fireplace");
    fireplaceMap.put("slots", fireplace);

    flashtalking.add("#ftwallpaper");
    flashtalkingMap.put("name", "flashtalking");
    flashtalkingMap.put("slots", flashtalking);

    wpIntern.add("#soi_wp_skyscraper1_outer");
    wpInternMap.put("name", "wp_intern");
    wpInternMap.put("slots", wpIntern);

    leaderboard.add("#ad-fullbanner2-outer");
    leaderboardMap.put("name", "leaderboard");
    leaderboardMap.put("slots", leaderboard);

    medrec.add("#ad-rectangle1");
    medrecMap.put("name", "medrec");
    medrecMap.put("slots", medrec);

    prefooter.add("#ad-promo1");
    prefooterMap.put("name", "prefooter");
    prefooterMap.put("slots", prefooter);

    combinations.add(billboardMap);
    combinations.add(fireplaceMap);
    combinations.add(flashtalkingMap);
    combinations.add(wpInternMap);
    combinations.add(leaderboardMap);
    combinations.add(medrecMap);
    combinations.add(prefooterMap);

  }

  public void verify71MediaAdsPresent() {
    AdsComparison adsComparison = new AdsComparison();
    Map testedCombination = Collections.emptyMap();

    for (Map<String, Object> combination : combinations) {
      if (checkIfCombinationOnPage((List<String>) combination.get("slots"))) {
        testedCombination = combination;
        break;
      }
    }

    if (testedCombination.isEmpty()) {
      throw new NoSuchElementException("No known combination from 71 media present");
    }

    PageObjectLogging.log(
        "Combination present",
        "Combination present: " + testedCombination.get("name"),
        true,
        driver
    );

    for (String slotSelector : (List<String>) testedCombination.get("slots")) {
      WebElement slot = driver.findElement(By.cssSelector(slotSelector));
      if (hasSkin(slot, slotSelector) ||
          adsComparison.isAdVisible(slot, slotSelector, driver)) {
        PageObjectLogging.log(
            "Ad in slot found",
            "Ad in slot found; CSS: " + slotSelector,
            true
        );
      } else {
        throw new NoSuchElementException("Ad in slot not found; CSS: " + slotSelector);
      }
    }
  }

  public void verifyNo71MediaAds() {
    for (Map<String, Object> combination : combinations) {
      if (checkIfCombinationOnPage((List<String>) combination.get("slots"))) {
        PageObjectLogging.log(
            "Combination present",
            "Combination present: " + combination.get("name"),
            false
        );
      }
    }
  }

  private boolean checkIfCombinationOnPage(List<String> combination) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    String script = "return $(arguments[0]).find('iframe, object, img').filter(':visible').length;";

    for (String elementSelector : combination) {
      if (isElementOnPage(By.cssSelector(elementSelector))) {
        if ((Long) js.executeScript(script, elementSelector) < 1) {
          return false;
        }
      } else {
        return false;
      }
    }
    return true;
  }

  private boolean hasSkin(WebElement element, String elementSelector) {
    if (checkScriptPresentInElement(element, JS_SKIN_CALL)) {
      PageObjectLogging.log("Found skin call", "skin call found in " + elementSelector, true);
      return true;
    }
    return false;
  }

  public void verify71MediaParams(String expectedParams) {
    ArrayList<String> actual = get71MediaParams();
    String[] expected = expectedParams.split("; ");
    for (int i = 0; i < expected.length; i++) {
      Assertion.assertEquals(actual.get(i), expected[i]);
    }
  }

  private ArrayList<String> get71MediaParams() {
    return (ArrayList<String>) ((JavascriptExecutor) driver).executeScript(
        "return ['SOI_SITE: ' + SOI_SITE, 'SOI_SUBSITE: ' + SOI_SUBSITE, 'SOI_SUB2SITE: ' + SOI_SUB2SITE, "
        +
        "'SOI_SUB3SITE: ' + SOI_SUB3SITE, 'SOI_CONTENT: ' + SOI_CONTENT, 'SOI_WERBUNG: ' + SOI_WERBUNG, "
        +
        "'SOI_KEYWORDS: ' + SOI_KEYWORDS];"
    );
  }
}
