package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;

@Test(groups = "VKWidget")
@InBrowser(browser = Browser.CHROME)
public class VKTests extends NewTestTemplate {

  private static final String VK_ONE_WIDGET_ARTICLE_NAME = "/wiki/VKOasis/OneWidget";
  private static final String VK_MULTIPLE_WIDGETS_ARTICLE_NAME = "/wiki/VKOasis/MultipleWidgets";
  private static final String VK_INCORRECT_WIDGET_ARTICLE_NAME = "/wiki/VKOasis/IncorrectWidget";

  private VKWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new VKWidgetPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "VKWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_001_isLoaded() {
    init();

    widget.create(VK_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(VK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }


  @Test(groups = "VKWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(VK_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(VK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "VKWidgetTest_003")
  @Execute(onWikia = "mercuryautomationtesting")
  public void VKWidgetTest_003_isErrorPresent() {
    init();

    widget.createIncorrect(VK_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage(VK_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
