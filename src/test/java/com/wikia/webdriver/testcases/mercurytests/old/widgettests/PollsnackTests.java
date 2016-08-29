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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "PollysnackWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class PollsnackTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private PollsnackWidgetPageObject widget;
  private Navigate navigate;

  private static final String POLLSNACK_ONE_WIDGET_ARTICLE_NAME = "/wiki/PollsnackMercury/OneWidget";
  private static final String POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME = "/wiki/PollsnackMercury/MultipleWidgets";
  private static final String POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME = "/wiki/PollsnackMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = POLLSNACK_ONE_WIDGET_ARTICLE_NAME.substring(6);

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.widget = new PollsnackWidgetPageObject(driver);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_001")
  public void MercuryPollsnackWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_002")
  public void MercuryPollsnackWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    init();

    widget.create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_003")
  public void MercuryPollsnackWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    init();

    widget.create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_004")
  public void MercuryPollsnackWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.createMultiple(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_005")
  public void MercuryPollsnackWidgetTest_005_isErrorPresent() {
    init();

    widget.createIncorrect(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
