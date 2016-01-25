package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.Navigation;
import com.wikia.webdriver.elements.mercury.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;

import org.testng.annotations.Test;

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

  private static final String GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME = "GoogleFormMercury/OneWidget";
  private static final String GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME = "GoogleFormMercury/MultipleWidgets";
  private static final String GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME = "GoogleFormercury/IncorrectWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);
    this.widget = new GoogleFormWidgetPageObject(driver);
    this.navigate = new Navigate(driver);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_001")
  public void MercuryGoogleFormWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_002")
  public void MercuryGoogleFormWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    init();

    widget.create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + MercurySubpages.MAIN_PAGE);
    topBar.openNavigation();
    navigation.navigateToPage(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_003")
  public void MercuryGoogleFormWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    init();

    widget.create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    topBar.openNavigation();
    navigation.navigateToPage(MAPS_ARTICLE_NAME);
    topBar.openNavigation();
    navigation.navigateToPage(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_004")
  public void MercuryGoogleFormWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.createMultiple(GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage("/wiki/" + GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_005")
  public void MercuryGoogleFormWidgetTest_005_isErrorPresent() {
    init();

    widget.createIncorrect(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
