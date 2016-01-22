package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.Navigation;
import com.wikia.webdriver.elements.mercury.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class PolldaddyTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;

  private static final String POLLDADDY_ONE_WIDGET_ARTICLE_NAME = "PollDaddyMercury/OneWidget";
  private static final String POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME = "PollDaddyMercury/MultipleWidgets";
  private static final String POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME = "PollDaddymercury/IncorrectWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  public void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_001")
  public void MercuryPolldaddyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
        .create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_002")
  public void MercuryPolldaddyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    init();
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
        .create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, MercurySubpages.MAIN_PAGE);

    topBar.openNavigation();
    navigation.navigateToPage(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_003")
  public void MercuryPolldaddyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    init();
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
        .create(POLLDADDY_ONE_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    topBar.openNavigation();
    navigation.navigateToPage(MAPS_ARTICLE_NAME);
    topBar.openNavigation();
    navigation.navigateToPage(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_004")
  public void MercuryPolldaddyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
        .createMultiple(POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_005")
  public void MercuryPolldaddyWidgetTest_005_isErrorPresent() {
    PolldaddyWidgetPageObject widget = new PolldaddyWidgetPageObject(driver);

    widget
        .createIncorrect(POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME)
        .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
