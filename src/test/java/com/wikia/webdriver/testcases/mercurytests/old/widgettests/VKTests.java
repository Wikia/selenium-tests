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
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_VKWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class VKTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private Navigate navigate;
  private VKWidgetPageObject widget;

  private static final String VK_ONE_WIDGET_ARTICLE_NAME = "/VKMercury/OneWidget";
  private static final String VK_MULTIPLE_WIDGETS_ARTICLE_NAME = "/VKMercury/MultipleWidgets";
  private static final String VK_INCORRECT_WIDGET_ARTICLE_NAME = "/VKMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = VK_ONE_WIDGET_ARTICLE_NAME;
  private static final String VALID_VK_TAG = "<vk group-id=\"59925174\" />\n";
  private static final String INVALID_VK_TAG = "<vk />";

  private void init() {
    this.topBar = new TopBar();
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.widget = new VKWidgetPageObject();
  }

  @Test(groups = "MercuryVKWidgetTest_001")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_VK_TAG, VK_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(VK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_002")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_VK_TAG, VK_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_003")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_VK_TAG, VK_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("VK tests 003", "Map");
    init();

    navigate.toPage(VK_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_004")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_VK_TAG + " " + VALID_VK_TAG,
            VK_MULTIPLE_WIDGETS_ARTICLE_NAME);
    init();

    navigate.toPage(VK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(
        widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG
    );

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_005")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_005_isErrorPresent() {
    new ArticleContent().push(INVALID_VK_TAG, VK_INCORRECT_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage(VK_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
