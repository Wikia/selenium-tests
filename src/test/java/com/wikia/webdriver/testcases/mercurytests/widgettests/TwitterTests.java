package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
// Uncoment after finish all widget tags
//@Test(groups = {"MercuryTwitterWidgetTests", "MercuryWidgetTests", "Mercury"})
public class TwitterTests extends NewTestTemplate {

  private static final String TWITTER_ARTICLE_NAME = "TwitterWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryTwitterWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryTwitterWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryTwitterWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(TWITTER_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryTwitterWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget.create().navigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(TWITTER_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryTwitterWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(
        widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG
    );

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryTwitterWidgetTest_005_isErrorPresent() {
    TwitterWidgetPageObject widget = new TwitterWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
