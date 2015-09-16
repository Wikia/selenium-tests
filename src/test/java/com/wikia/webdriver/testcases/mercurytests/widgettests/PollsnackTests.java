package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
// Uncoment after finish all widget tags
//@Test(groups = {"MercuryPollsnackWidgetTests", "MercuryWidgetTests", "Mercury"})
public class PollsnackTests extends NewTestTemplate {

  private static final String SOUND_CLOUD_ARTICLE_NAME = "PollsnackWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(SOUND_CLOUD_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.create().navigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(SOUND_CLOUD_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_005_isErrorPresent() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
