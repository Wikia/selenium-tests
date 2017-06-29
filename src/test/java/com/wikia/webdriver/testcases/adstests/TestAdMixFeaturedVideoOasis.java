package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.contentpatterns.AdsContent;
import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.TemplateNoFirstLoad;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsOoyalaObject;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TestAdMixFeaturedVideoOasis extends TemplateNoFirstLoad {

  @Test(
      dataProviderClass = AdsDataProvider.class,
      groups = {"AdMixFeaturedVideoOasis"},
      dataProvider = "adMixFeaturedVideoOasis"
  )
  public void adMixFeaturedVideoOasis(String wikiName, String article) {
    Wait wait = new Wait(driver);
    JavascriptActions jsActions = new JavascriptActions(driver);
    String testedPage = urlBuilder.getUrlForPath(wikiName, article + "?InstantGlobals.wgAdDriverAdMixCountries=[XX]");
    AdsOoyalaObject wikiPage = new AdsOoyalaObject(driver, testedPage);
    wikiPage.verifyPlayerOnPage();
    wikiPage.verifyTopLeaderboard();
    wikiPage.verifyMedrec();
    wikiPage.verifyRecirculationRightRailModule();

    // verify as soon as the div gets sticky, the recirc changes to FMR
    jsActions.scrollBy(0, 1000);
    wikiPage.verifyFloatingMedrec();
    wikiPage.verifyPlayerOnPage();

    // verify floating medrec switched to a recirc module after ~10sec & slight scroll
    wait.forXMilliseconds(10000);
    jsActions.scrollBy(0, 100);
    wikiPage.verifyRecirculationRightRailModule();
    wait.forElementNotVisible(By.id(AdsContent.FLOATING_MEDREC));
    wikiPage.verifyPlayerOnPage();

    // verify recirc module switched to a floating medrec after ~10sec & slight scroll
    wait.forXMilliseconds(10000);
    jsActions.scrollBy(0, 100);
    wikiPage.verifyFloatingMedrec();
    wait.forElementNotVisible(By.id("recirculation-rail"));
    wikiPage.verifyPlayerOnPage();
  }
}
