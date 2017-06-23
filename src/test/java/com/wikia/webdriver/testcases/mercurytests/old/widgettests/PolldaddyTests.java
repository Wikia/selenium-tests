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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PolldaddyWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_PollydaddyWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class PolldaddyTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private Navigate navigate;
  private PolldaddyWidgetPageObject widget;

  private static final String POLLDADDY_ONE_WIDGET_ARTICLE_NAME = "PollDaddyMercury/OneWidget";
  private static final String POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME = "PollDaddyMercury/MultipleWidgets";
  private static final String POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME = "PollDaddymercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = POLLDADDY_ONE_WIDGET_ARTICLE_NAME;
  private static final String VALID_POLLDADDY_TAG = "<polldaddy id=\"8956579\"/>\n";
  private static final String VALID_POLLDADDY_TAG_2 = "<polldaddy id=\"9022741\"/>";
  private static final String INVALID_POLLDADDY_TAG = "<polldaddy />";

  private void init() {
    this.topBar = new TopBar();
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.widget = new PolldaddyWidgetPageObject();
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_001")
  public void MercuryPolldaddyWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_POLLDADDY_TAG, POLLDADDY_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_002")
  public void MercuryPolldaddyWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_POLLDADDY_TAG, POLLDADDY_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPageByPath(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_003")
  public void MercuryPolldaddyWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_POLLDADDY_TAG, POLLDADDY_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Polldaddy Widget 003", "Map");
    init();

    navigate.toPage(POLLDADDY_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_004")
  public void MercuryPolldaddyWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_POLLDADDY_TAG + " " + VALID_POLLDADDY_TAG_2,
            POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME);
    init();

    navigate.toPage(POLLDADDY_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPolldaddyWidgetTest_005")
  public void MercuryPolldaddyWidgetTest_005_isErrorPresent() {
    new ArticleContent().push(INVALID_POLLDADDY_TAG, POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(POLLDADDY_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
