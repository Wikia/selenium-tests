package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.joda.time.DateTime;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by wikia on 2015-07-15.
 */
public class MercuryBasePageObject extends BasePageObject {

  public MercuryBasePageObject(WebDriver driver) {
    super(driver);
  }

  public CuratedMainPagePageObject openCuratedMainPage(String wikiURL, String mainPage) {
    getUrl(wikiURL + URLsContent.WIKI_DIR + mainPage + "?cb=" + DateTime.now().getMillis());
    PageObjectLogging
        .log("openCuratedMainPage", "Curated main page" + mainPage + " was opened", true);
    return new CuratedMainPagePageObject(driver);
  }

  private enum Settings {
    TIME_OUT_IN_SEC(5),
    CHECK_OUT_IN_MILLI_SEC(1000);

    private int value;

    private Settings(int value) {
      this.value = value;
    }
  }

  public boolean isElementVisible(WebElement element) {
    try {
      waitForElementVisibleByElement(element, Settings.TIME_OUT_IN_SEC.value,
                                     Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }
}
