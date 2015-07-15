package com.wikia.webdriver.testcases.mercurytests.curatedcontenttests;

import com.wikia.webdriver.common.contentpatterns.MercuryArticles;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedCategoryPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.CuratedMainPagePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.mercury.MercuryBasePageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NavigationTests extends NewTestTemplate {

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  // CCT06
  @Test(groups = {"MercuryNavigationTests_001", "MercuryNavigationTests", "Mercury"})
  public void MercuryNavigationTests_001_navigateThroughCategory() {

    wikiURL = urlBuilder.getUrlForWiki(MercuryWikis.MERCURY_CC);

    MercuryBasePageObject base = new MercuryBasePageObject(driver);
    CuratedMainPagePageObject mainPage;
    mainPage = base.openCuratedMainPage(wikiURL, MercuryCuratedMainPages.CC_MAIN_PAGE);
    CuratedCategoryPageObject category = mainPage.tapOnCuratedElement(2);
    CuratedMainPagePageObject cc = new CuratedMainPagePageObject(driver);


    cc.openMercuryArticleByName(wikiURL, MercuryArticles.CC_MAIN_PAGE);


  }

  // CCT07
  @Test(groups = {"MercuryNavigationTests_001", "MercuryNavigationTests", "Mercury"})
  public void MercuryNavigationTests_002_navigateThroughSection() {


  }

}
