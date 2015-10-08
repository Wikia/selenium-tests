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
 * @ownership Content X-Wing Wikia
 */
@Test(groups = {"MercuryGoogleFormWidgetTests", "MercuryWidgetTests", "Mercury"})
public class GoogleFormTests extends NewTestTemplate {

  private static String GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME = "GoogleFormMercury/OneWidget";
  private static String GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME = "GoogleFormMercury/MultipleWidgets";
  private static String GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME = "GoogleFormercury/IncorrectWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget
      .create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget
      .create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    new NavigationSideComponentObject(driver).navigateToArticle(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget
      .create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    new NavigationSideComponentObject(driver)
      .navigateToArticle(MAPS_ARTICLE_NAME)
      .navigateToArticle(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget
      .createMultiple(GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_005_isErrorPresent() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget
      .createIncorrect(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
