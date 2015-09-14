package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
public class VKTests extends NewTestTemplate {

  private static final String VK_ARTICLE_NAME = "VKWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercuryVKWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryVKWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget.createAndNavigate(wikiURL);
    Assertion.assertTrue(vkWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryVKWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(VK_ARTICLE_NAME);

    Assertion.assertTrue(vkWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryVKWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    VKWidgetPageObject vkWidget = new VKWidgetPageObject(driver);

    vkWidget.createAndNavigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(VK_ARTICLE_NAME);

    Assertion.assertTrue(vkWidget.isLoadedOnMercury(), MercuryMessages.INVISIBLE_MSG);
  }
}
