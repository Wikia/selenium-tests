package com.wikia.webdriver.testcases.mobile.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.components.navigation.global.GlobalNavigationMobile;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WeiboWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;
@Test(groups = "Mercury_WeiboWidget")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class WeiboTests extends NewTestTemplate {

  private static final String WEIBO_ONE_WIDGET_ARTICLE_NAME = "WeiboMercury/OneWidget";
  private static final String WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME = "WeiboMercury/MultipleWidgets";
  private static final String WEIBO_INCORRECT_WIDGET_ARTICLE_NAME = "WeiboMercury/IncorrectWidget";
  private static final String QUERY_1 = MobileSubpages.MAP;
  private static final String QUERY_2 = WEIBO_ONE_WIDGET_ARTICLE_NAME;

  @Test(groups = "MercuryWeiboWidgetTest_001")
  public void MercuryWeiboWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new WeiboWidgetPageObject().create(WEIBO_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(WEIBO_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_002")
  public void MercuryWeiboWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new WeiboWidgetPageObject().create(WEIBO_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(MobileSubpages.MAIN_PAGE);
    new GlobalNavigationMobile().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_003")
  public void MercuryWeiboWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new WeiboWidgetPageObject().create(WEIBO_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Weibo test 003", "Map");

    new Navigate().toPage(WEIBO_ONE_WIDGET_ARTICLE_NAME);
    new GlobalNavigationMobile().openSearch().navigateToPage(QUERY_1);
    new GlobalNavigationMobile().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_004")
  public void MercuryWeiboWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new WeiboWidgetPageObject().createMultiple(WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);

    new Navigate().toPage(WEIBO_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryWeiboWidgetTest_005")
  public void MercuryWeiboWidgetTest_005_isErrorPresent() {
    WidgetPageObject widget =
            new WeiboWidgetPageObject().createIncorrect(WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(WEIBO_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
