package com.wikia.webdriver.testcases.mobilewikitests.widgettests;

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
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PollsnackWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;
@Test(groups = "Mercury_PollysnackWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class PollsnackTests extends NewTestTemplate {

  private static final String POLLSNACK_ONE_WIDGET_ARTICLE_NAME = "PollsnackMercury/OneWidget";
  private static final String POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME = "PollsnackMercury/MultipleWidgets";
  private static final String POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME = "PollsnackMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = POLLSNACK_ONE_WIDGET_ARTICLE_NAME;

  @Test(groups = "MercuryPollsnackWidgetTest_001")
  public void MercuryPollsnackWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new PollsnackWidgetPageObject().create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_002")
  public void MercuryPollsnackWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new PollsnackWidgetPageObject().create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPageByPath(MercurySubpages.MAIN_PAGE);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_003")
  public void MercuryPollsnackWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new PollsnackWidgetPageObject().create(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Pollsnack test 003", "Map");

    new Navigate().toPage(POLLSNACK_ONE_WIDGET_ARTICLE_NAME);
    new TopBar().openSearch().navigateToPage(QUERY_1);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_004")
  public void MercuryPollsnackWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new PollsnackWidgetPageObject().createMultiple(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    new Navigate().toPage(POLLSNACK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPollsnackWidgetTest_005")
  public void MercuryPollsnackWidgetTest_005_isErrorPresent() {
    WidgetPageObject widget =
            new PollsnackWidgetPageObject().createIncorrect(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(POLLSNACK_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
