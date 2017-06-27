package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "VKWidget")
@InBrowser(browser = Browser.CHROME)
public class VKTests extends NewTestTemplate {

  private static final String VK_ONE_WIDGET_ARTICLE_NAME = "VKOasis_OneWidget";
  private static final String VK_MULTIPLE_WIDGETS_ARTICLE_NAME = "VKOasis_MultipleWidgets";
  private static final String VK_INCORRECT_WIDGET_ARTICLE_NAME = "VKOasis_IncorrectWidget";

  @Test(groups = "VKWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_001_isLoaded() {
    WidgetPageObject widget =
            new VKWidgetPageObject().create(VK_ONE_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(VK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }


  @Test(groups = "VKWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_002_areLoaded() {
    WidgetPageObject widget =
            new VKWidgetPageObject().createMultiple(VK_MULTIPLE_WIDGETS_ARTICLE_NAME);
    new ArticlePageObject().open(VK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "VKWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_003_isErrorPresent() {
    WidgetPageObject widget =
            new VKWidgetPageObject().createIncorrect(VK_INCORRECT_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(VK_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
