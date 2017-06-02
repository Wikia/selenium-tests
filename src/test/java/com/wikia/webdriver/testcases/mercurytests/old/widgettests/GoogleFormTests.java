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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_GoogleFormWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class GoogleFormTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private GoogleFormWidgetPageObject widget;
  private Navigate navigate;

  private static final String GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME = "/wiki/GoogleFormMercury/OneWidget";
  private static final String GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME = "/wiki/GoogleFormMercury_MultipleWidgets";
  private static final String GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME = "/wiki/GoogleFormercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME.substring(6);

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);
    this.widget = new GoogleFormWidgetPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_001")
  public void MercuryGoogleFormWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_002")
  public void MercuryGoogleFormWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    init();

    widget.create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(MercurySubpages.MAIN_PAGE);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_003")
  public void MercuryGoogleFormWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    init();

    widget.create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_004")
  public void MercuryGoogleFormWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.createMultiple("GoogleFormMercury_OneWidget");
    navigate.toPage(GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_005")
  public void MercuryGoogleFormWidgetTest_005_isErrorPresent() {
    init();

    widget.createIncorrect(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
