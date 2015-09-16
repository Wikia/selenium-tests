package com.wikia.webdriver.testcases.mercurytests.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mercury.NavigationSideComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @ownership: Content X-Wing
 */
// Uncoment after finish all widget tags
//@Test(groups = {"MercurySoundCloudWidgetTests", "MercuryWidgetTests", "Mercury"})
public class SoundCloudTests extends NewTestTemplate {

  private static final String SOUND_CLOUD_ARTICLE_NAME = "SoundCloudWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  @BeforeMethod(alwaysRun = true)
  public void prepareTest() {
    driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
  }

  @Test(groups = "MercurySoundCloudWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySoundCloudWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    SoundCloudWidgetPageObject widget = new SoundCloudWidgetPageObject(driver);

    widget.create().navigate(wikiURL);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundCloudWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySoundCloudWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    SoundCloudWidgetPageObject widget = new SoundCloudWidgetPageObject(driver);

    widget
        .create()
        .openMercuryArticleByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);
    new NavigationSideComponentObject(driver).navigateToArticle(SOUND_CLOUD_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundCloudWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySoundCloudWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    SoundCloudWidgetPageObject widget = new SoundCloudWidgetPageObject(driver);

    widget.create().navigate(wikiURL);

    new NavigationSideComponentObject(driver)
        .navigateToArticle(MAPS_ARTICLE_NAME)
        .navigateToArticle(SOUND_CLOUD_ARTICLE_NAME);

    Assertion
        .assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundcloudWidgetTest_004")
  @Execute(onWikia = "mercuryautomationtesting")
  public void MercurySoundcloudWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    SoundCloudWidgetPageObject widget = new SoundCloudWidgetPageObject(driver);

    widget.createMultiple().navigate(wikiURL);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }
}
