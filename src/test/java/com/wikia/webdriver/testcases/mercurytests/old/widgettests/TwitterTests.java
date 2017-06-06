package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_TwitterWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class TwitterTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private Navigate navigate;
  private TwitterWidgetPageObject widget;

  private static final String TWITTER_ONE_WIDGET_ARTICLE_NAME = "/wiki/TwitterMercury/OneWidget";
  private static final String TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME = "/wiki/TwitterMercury/MultipleWidgets";
  private static final String TWITTER_INCORRECT_WIDGET_ARTICLE_NAME = "TwitterMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = TWITTER_ONE_WIDGET_ARTICLE_NAME.substring(6);

  private void init() {
    this.topBar = new TopBar();
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.widget = new TwitterWidgetPageObject();
  }

  @Test(groups = "MercuryTwitterWidgetTest_001")
  public void MercuryTwitterWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.create(TWITTER_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(TWITTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_002")
  public void MercuryTwitterWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    init();

    widget.create(TWITTER_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_003")
  public void MercuryTwitterWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    init();

    widget.create(TWITTER_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(TWITTER_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_004")
  public void MercuryTwitterWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.createMultiple(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(
        widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG
    );

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_005")
  public void MercuryTwitterWidgetTest_005_isErrorPresent() {
    init();

    widget.createIncorrect(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
