package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.testng.annotations.Test;

public class TestAdMixFeaturedVideoOasis extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdMixFeaturedVideoOasis"},
      dataProvider = "adMixFeaturedVideoOasis"
  )
  public void adMixFeaturedVideoOasis(String wikiName, String article) {
    JavascriptActions jsActions = new JavascriptActions(driver);
    String testedPage = urlBuilder.getUrlForPath(wikiName, article + "?InstantGlobals.wgAdDriverAdMixCountries=[XX]");
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyPlayerOnPage();
    wikiPage.verifyTopLeaderboard();
    wikiPage.verifyMedrec();
    // verify recirc remains fixed for ~700px,
    jsActions.scrollBy(0, 600);
    // verify recirc switched to a 3x2 after ~1400px && 10secs
    // verify recirc switches back to recirc for the rest of the page
  }
}
