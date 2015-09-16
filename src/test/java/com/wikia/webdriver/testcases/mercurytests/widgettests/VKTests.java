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
    VKWidgetPageObject widget = new VKWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryVKWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    VKWidgetPageObject widget = new VKWidgetPageObject(driver);

    widget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(VK_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryVKWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    VKWidgetPageObject widget = new VKWidgetPageObject(driver);

    widget.create().navigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(VK_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryVKWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    VKWidgetPageObject widget = new VKWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(
        widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG
    );

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_005")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercuryVKWidgetTest_005_isErrorPresent() {
    VKWidgetPageObject widget = new VKWidgetPageObject(driver);

    widget.createIncorrect().navigate(wikiURL);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
