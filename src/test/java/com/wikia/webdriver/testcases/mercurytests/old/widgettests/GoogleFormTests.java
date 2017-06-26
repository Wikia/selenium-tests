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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;
@Test(groups = "Mercury_GoogleFormWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class GoogleFormTests extends NewTestTemplate {

  private static final String GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME = "GoogleFormMercury/OneWidget";
  private static final String GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME = "GoogleFormMercury/MultipleWidgets";
  private static final String GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME = "GoogleFormMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME;

  @Test(groups = "MercuryGoogleFormWidgetTest_001")
  public void MercuryGoogleFormWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new GoogleFormWidgetPageObject().create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_002")
  public void MercuryGoogleFormWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new GoogleFormWidgetPageObject().create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPageByPath(MercurySubpages.MAIN_PAGE);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_003")
  public void MercuryGoogleFormWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new GoogleFormWidgetPageObject().create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Mercury Google Form 003", "Map");

    new Navigate().toPage(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    new TopBar().openSearch().navigateToPage(QUERY_1);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_004")
  public void MercuryGoogleFormWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new GoogleFormWidgetPageObject().createMultiple(GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    new Navigate().toPage(GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryGoogleFormWidgetTest_005")
  public void MercuryGoogleFormWidgetTest_005_isErrorPresent() {
    WidgetPageObject widget =
            new GoogleFormWidgetPageObject().createIncorrect(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
