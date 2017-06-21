package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_PollysnackWidget")
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

  private static final String POLLSNACK_ONE_WIDGET_ARTICLE_NAME = "/PollsnackMercury/OneWidget";
  private static final String POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME = "/PollsnackMercury/MultipleWidgets";
  private static final String POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME = "/PollsnackMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = POLLSNACK_ONE_WIDGET_ARTICLE_NAME;
  private static final String VALID_POLLSNACK_TAG = "<pollsnack hash=\"q7kiw9kz\"/>\n";
  private static final String INVALID_POLLSNACK_TAG = "<pollsnack />";

  private void init() {
    this.topBar = new TopBar();
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.widget = new PollsnackWidgetPageObject();
  }

  @Test(groups = "MercuryPollsnackWidgetTest_001")
  public void MercuryPollsnackWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_POLLSNACK_TAG, POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_002")
  public void MercuryPollsnackWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_POLLSNACK_TAG, POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_003")
  public void MercuryPollsnackWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_POLLSNACK_TAG, POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Pollsnack test 003", "Map");
    init();

    navigate.toPage(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_004")
  public void MercuryPollsnackWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_POLLSNACK_TAG + " " + VALID_POLLSNACK_TAG,
            POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);
    init();

    navigate.toPage(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_005")
  public void MercuryPollsnackWidgetTest_005_isErrorPresent() {
    new ArticleContent().push(INVALID_POLLSNACK_TAG, POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
