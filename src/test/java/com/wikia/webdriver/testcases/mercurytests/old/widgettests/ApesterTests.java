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
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.ApesterWidgetPageObject;

import org.testng.annotations.Test;

@Test(groups = "Mercury_ApesterWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class ApesterTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigate navigate;
  private ApesterWidgetPageObject widget;

  private static final String APESTER_ONE_WIDGET_ARTICLE_NAME = "ApesterMercury/OneWidget";
  private static final String APESTER_MULTIPLE_WIDGETS_ARTICLE_NAME = "ApesterMercury/MultipleWidgets";
  private static final String APESTER_INCORRECT_WIDGET_ARTICLE_NAME = "ApesterMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = APESTER_ONE_WIDGET_ARTICLE_NAME;
  private static final String VALID_APESTER_TAG = "<apester data-media-id=\"58d3c0fa6d8f378c033d1d39\" />";
  private static final String INVALID_APESTER_TAG = "<apester />";

  private void init() {
    this.topBar = new TopBar();
    this.navigate = new Navigate();
    this.widget = new ApesterWidgetPageObject();
  }

  @Test(groups = "MercuryApesterWidgetTest_001")
  public void MercuryApesterWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_APESTER_TAG, APESTER_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage("/" + APESTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryApesterWidgetTest_002")
  public void MercuryApesterWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_APESTER_TAG, APESTER_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryApesterWidgetTest_003")
  public void MercuryApesterWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push("Apester Test 003", "Map");
    new ArticleContent().push(VALID_APESTER_TAG, APESTER_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage("/" + APESTER_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryApesterWidgetTest_004")
  public void MercuryApesterWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_APESTER_TAG + " " + VALID_APESTER_TAG,
            APESTER_MULTIPLE_WIDGETS_ARTICLE_NAME);
    init();

    navigate.toPage("/" + APESTER_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryApesterWidgetTest_005")
  public void MercuryApesterWidgetTest_005_isErrorPresent() {
    new ArticleContent().push(INVALID_APESTER_TAG, APESTER_INCORRECT_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage("/" + APESTER_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
