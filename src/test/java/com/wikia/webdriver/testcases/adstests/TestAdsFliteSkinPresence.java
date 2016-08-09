package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsFliteObject;

import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class TestAdsFliteSkinPresence extends TemplateNoFirstLoad {

  private static final String[] FLITE_SYNTHETIC_SUPERHEROES_IFRAMES = new String[]{
      "iframe[id$=TOP_LEADERBOARD_0]",
      "#f_ad_dd9465c8-a687-46cd-8550-b6ee529f76ed",
      ".flite-ad"
  };

  @RelatedIssue(issueID = "ADEN-3530")
  @Test(
      dataProviderClass = AdsDataProvider.class,
      dataProvider = "fliteSkin",
      groups = "AdsFliteSkinPresenceOasis"
  )
  public void adsFliteSkinPresence(String wikiName,
                                   String article,
                                   Dimension windowResolution,
                                   List<List<String>> skinData) throws IOException {
    String testedPage = urlBuilder.getUrlForPath(wikiName, article);
    PageObjectLogging.log("Window resolution: ", String.valueOf(windowResolution.width), true);

    AdsFliteObject adsFliteObject = new AdsFliteObject(driver,
                                                       testedPage,
                                                       windowResolution,
                                                       FLITE_SYNTHETIC_SUPERHEROES_IFRAMES);

    for (List<String> skin : skinData) {
      adsFliteObject.verifyFliteSkin(skin.get(0), skin.get(1), skin.get(2));
    }
  }
}
