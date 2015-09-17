package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
// Enable after 22-sep
//@Test(groups = {"MercuryGoogleFormWidgetTests", "MercuryWidgetTests", "Mercury"})
public class GoogleFormTests extends NewTestTemplate {

  private static final String GOOGLE_FORM_ARTICLE_NAME = "GoogleFormWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    GoogleFormWidgetPageObject googleFormWidget = new GoogleFormWidgetPageObject(driver);

    googleFormWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(googleFormWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    GoogleFormWidgetPageObject googleFormWidget = new GoogleFormWidgetPageObject(driver);

    googleFormWidget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(GOOGLE_FORM_ARTICLE_NAME);

    Assertion.assertTrue(googleFormWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    GoogleFormWidgetPageObject googleFormWidget = new GoogleFormWidgetPageObject(driver);

    googleFormWidget.createAndNavigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(GOOGLE_FORM_ARTICLE_NAME);

    Assertion
        .assertTrue(googleFormWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }
}
