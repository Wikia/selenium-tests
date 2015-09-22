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

  private static final String POLLDADDY_ARTICLE_NAME = "PolldaddyWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(POLLDADDY_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget.create().navigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(POLLDADDY_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryGoogleFormWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPolldaddyWidgetTest_005_isErrorPresent() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
