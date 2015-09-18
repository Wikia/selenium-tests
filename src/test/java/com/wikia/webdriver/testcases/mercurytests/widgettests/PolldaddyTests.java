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

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"MercuryPolldaddyWidgetTests", "MercuryWidgetTests", "Mercury"})
public class PolldaddyTests extends NewTestTemplate {

  private static final String POLLSNACK_ARTICLE_NAME = "PolldaddyWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    PolldaddyWidgetPageObject polldaddyWidget = new PolldaddyWidgetPageObject(driver);

    polldaddyWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(polldaddyWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    PolldaddyWidgetPageObject polldaddyWidget = new PolldaddyWidgetPageObject(driver);

    polldaddyWidget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(POLLSNACK_ARTICLE_NAME);

    Assertion.assertTrue(polldaddyWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    PolldaddyWidgetPageObject polldaddyWidget = new PolldaddyWidgetPageObject(driver);

    polldaddyWidget.createAndNavigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(POLLSNACK_ARTICLE_NAME);

    Assertion.assertTrue(polldaddyWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_004_isErrorPresent() {
    PolldaddyWidgetPageObject polldaddyWidget = new PolldaddyWidgetPageObject(driver);

    polldaddyWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(polldaddyWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
