package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;

import org.testng.annotations.Test;

@Test(groups = {"VKWidgetTests", "WidgetTests"})
public class VKTests extends NewTestTemplate {

  private static String VK_ONE_WIDGET_ARTICLE_NAME = "VKOasis/OneWidget";
  private static String VK_MULTIPLE_WIDGETS_ARTICLE_NAME = "VKOasis/MultipleWidgets";
  private static String VK_INCORRECT_WIDGET_ARTICLE_NAME = "VKOasis/IncorrectWidget";

  @Test(groups = "VKWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_001_isLoaded() {
    VKWidgetPageObject widget = new VKWidgetPageObject(driver);

    widget
      .create(VK_ONE_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, VK_ONE_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }


  @Test(groups = "VKWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_002_areLoaded() {
    VKWidgetPageObject widget = new VKWidgetPageObject(driver);

    widget
      .createMultiple(VK_MULTIPLE_WIDGETS_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, VK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(
        widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG
    );

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "VKWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_003_isErrorPresent() {
    VKWidgetPageObject widget = new VKWidgetPageObject(driver);

    widget
      .createIncorrect(VK_INCORRECT_WIDGET_ARTICLE_NAME)
      .openArticleOnWikiByNameWithCbAndNoAds(wikiURL, VK_INCORRECT_WIDGET_ARTICLE_NAME);
    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
