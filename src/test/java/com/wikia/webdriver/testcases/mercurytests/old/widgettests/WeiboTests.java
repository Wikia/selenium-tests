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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_WeiboWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class WeiboTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private Navigate navigate;
  private WeiboWidgetPageObject widget;

  private static final String WEIBO_ONE_WIDGET_ARTICLE_NAME = "/WeiboMercury/OneWidget";
  private static final String WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME = "/WeiboMercury/MultipleWidgets";
  private static final String WEIBO_INCORRECT_WIDGET_ARTICLE_NAME = "/WeiboMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = WEIBO_ONE_WIDGET_ARTICLE_NAME;
  private static final String VALID_WEIBO_TAG = "<weibo uids=\"1642909335,1782515283\" />\n";
  private static final String INVALID_WEIBO_TAG = "<weibo />";

  private void init() {
    this.topBar = new TopBar();
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.widget = new WeiboWidgetPageObject();
  }

  @Test(groups = "MercuryWeiboWidgetTest_001")
  public void MercuryWeiboWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_WEIBO_TAG, WEIBO_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(WEIBO_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_002")
  public void MercuryWeiboWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_WEIBO_TAG, WEIBO_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_003")
  public void MercuryWeiboWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_WEIBO_TAG, WEIBO_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Weibo test 003", "Map");
    init();

    navigate.toPage(WEIBO_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_004")
  public void MercuryWeiboWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_WEIBO_TAG + " " + VALID_WEIBO_TAG,
            WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);
    init();

    navigate.toPage(WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_005")
  public void MercuryWeiboWidgetTest_005_isErrorPresent() {
    new ArticleContent().push(INVALID_WEIBO_TAG, WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
