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
    PollsnackWidgetPageObject pollsnackWidget = new PollsnackWidgetPageObject(driver);

    pollsnackWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(pollsnackWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    PollsnackWidgetPageObject pollsnackWidget = new PollsnackWidgetPageObject(driver);

    pollsnackWidget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(SOUND_CLOUD_ARTICLE_NAME);

    Assertion.assertTrue(pollsnackWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    PollsnackWidgetPageObject pollsnackWidget = new PollsnackWidgetPageObject(driver);

    pollsnackWidget.createAndNavigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(SOUND_CLOUD_ARTICLE_NAME);

    Assertion.assertTrue(pollsnackWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryPollsnackWidgetTest_005_isErrorPresent() {
    PollsnackWidgetPageObject pollsnackWidget = new PollsnackWidgetPageObject(driver);

    pollsnackWidget.createIncorrectAndNavigate(wikiURL);
    Assertion.assertTrue(pollsnackWidget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
