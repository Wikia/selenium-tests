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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"MercuryWeiboWidgetTests", "MercuryWidgetTests", "Mercury"})
public class WeiboTests extends NewTestTemplate {

  private static final String WEIBO_ARTICLE_NAME = "WeiboWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryWeiboWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWeiboWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWeiboWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget.create().openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(WEIBO_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWeiboWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget.create().navigate(wikiURL);

    new NavigationSideComponentObject(driver).navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(WEIBO_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_004", enabled = false)
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWeiboWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_005", enabled = false)
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWeiboWidgetTest_005_isErrorPresent() {
    WeiboWidgetPageObject widget = new WeiboWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
