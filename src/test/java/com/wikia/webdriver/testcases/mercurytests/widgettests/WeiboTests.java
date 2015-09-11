package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
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
    WeiboWidgetPageObject weiboWidget = new WeiboWidgetPageObject(driver);

    weiboWidget.createAndNavigate(wikiURL);
    Assertion
        .assertTrue(weiboWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWeiboWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WeiboWidgetPageObject weiboWidget = new WeiboWidgetPageObject(driver);

    weiboWidget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(WEIBO_ARTICLE_NAME);

    Assertion
        .assertTrue(weiboWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryWeiboWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WeiboWidgetPageObject weiboWidget = new WeiboWidgetPageObject(driver);

    weiboWidget.createAndNavigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(WEIBO_ARTICLE_NAME);

    Assertion
        .assertTrue(weiboWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

}
