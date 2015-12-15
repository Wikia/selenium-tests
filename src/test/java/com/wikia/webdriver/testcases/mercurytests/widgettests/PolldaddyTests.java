package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test(groups = {"MercuryPolldaddyWidgetTests", "MercuryWidgetTests", "Mercury"})
public class PolldaddyTests extends NewTestTemplate {

  private static String POLLDADDY_ONE_WIDGET_ARTICLE_NAME = "PollDaddyMercury/OneWidget";
  private static String POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME = "PollDaddyMercury/MultipleWidgets";
  private static String POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME = "PollDaddymercury/IncorrectWidget";

  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
      .create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
      .create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    new NavigationSideComponentObject(driver).navigateToArticle(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
      .create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    new NavigationSideComponentObject(driver)
      .navigateToArticle(MAPS_ARTICLE_NAME)
      .navigateToArticle(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
      .createMultiple(POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_005_isErrorPresent() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
      .createIncorrect(POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
