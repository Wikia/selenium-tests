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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PlaybuzzWidgetPageObject;

import org.testng.annotations.Test;

@Test(groups = "Mercury_PlaybuzzWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class PlaybuzzTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigate navigate;
  private PlaybuzzWidgetPageObject widget;

  private static final String PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME = "/PlaybuzzMercury/OneWidget";
  private static final String PLAYBUZZ_MULTIPLE_WIDGETS_ARTIVLE_NAME = "/PlaybuzzMercury/MultipleWidgets";
  private static final String PLAYBUZZ_INCORRECT_WIDGET_ARTICLE_NAME = "/PlaybuzzMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME;
  private static final String VALID_PLAYBUZZ_TAG = "<playbuzz data-item=\"b534ce26-d47f-455f-9e80-7702ee4c5c2b\" />";
  private static final String INVALID_PLAYBUZZ_TAG = "<playbuzz />";

  private void init() {
    this.topBar = new TopBar();
    this.navigate = new Navigate();
    this.widget = new PlaybuzzWidgetPageObject();
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_001")
  public void MercuryPlaybuzzWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_PLAYBUZZ_TAG, PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_002")
  public void MercuryPlaybuzzWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_PLAYBUZZ_TAG, PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_003")
  public void MercuryPlaybuzzWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_PLAYBUZZ_TAG, PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Playbuzz Mercury Widget 003", "Map");
    init();

    navigate.toPage(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_004")
  public void MercuryPlaybuzzWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_PLAYBUZZ_TAG + " " + VALID_PLAYBUZZ_TAG,
            PLAYBUZZ_MULTIPLE_WIDGETS_ARTIVLE_NAME);
    init();

    navigate.toPage(PLAYBUZZ_MULTIPLE_WIDGETS_ARTIVLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_005")
  public void MercuryPlaybuzzWidgetTest_005_isErrorPresent() {
    new ArticleContent().push(INVALID_PLAYBUZZ_TAG, PLAYBUZZ_INCORRECT_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(PLAYBUZZ_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
