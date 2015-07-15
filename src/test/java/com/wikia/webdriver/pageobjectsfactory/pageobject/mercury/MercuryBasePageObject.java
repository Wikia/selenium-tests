package com.wikia.webdriver.pageobjectsfactory.pageobject.mercury;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

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
}
