package com.wikia.webdriver.testcases.mercurytests.widgettests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"MercuryPollsnackWidgetTests", "MercuryWidgetTests", "Mercury"})
public class PollsnackTests extends NewTestTemplate {

  private static final String POLLSNACK_ARTICLE_NAME = "PollsnackWidget";
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

    widget.create().openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(POLLSNACK_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.create().navigate(wikiURL);

    new NavigationSideComponentObject(driver).navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(POLLSNACK_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_004", enabled = false)
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_005", enabled = false)
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_005_isErrorPresent() {
    PollsnackWidgetPageObject widget = new PollsnackWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
