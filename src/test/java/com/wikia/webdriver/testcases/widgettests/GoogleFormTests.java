package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.GoogleFormWidgetPageObject;

import org.testng.annotations.Test;

/**
 * @ownership: Content X-Wing
 */
@Test(groups = {"GoogleFormWidgetTests", "WidgetTests"})
public class GoogleFormTests extends NewTestTemplate {

  private static String GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME = "GoogleFormOasis/OneWidget";
  private static String GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME = "GoogleFormOasis/MultipleWidgets";
  private static String GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME = "GoogleFormOasis/IncorrectWidget";

  @Test(groups = "GoogleFormWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void GoogleFormWidgetTest_001_isLoaded() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget
      .create(GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, GOOGLE_FORM_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "GoogleFormWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void PollsnackWidgetTest_002_areLoaded() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget
      .createMultiple(GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, GOOGLE_FORM_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "GoogleFormWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void GoogleFormWidgetTest_003_isErrorPresent() {
    GoogleFormWidgetPageObject widget = new GoogleFormWidgetPageObject(driver);

    widget
      .createIncorrect(GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, GOOGLE_FORM_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
