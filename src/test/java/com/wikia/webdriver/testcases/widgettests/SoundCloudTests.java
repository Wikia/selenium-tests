package com.wikia.webdriver.testcases.widgettests;

import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;

@Test(groups = "SoundCloudWidget")
@InBrowser(browser = Browser.CHROME)
public class SoundCloudTests extends NewTestTemplate {

  private static final String SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME =
      "/wiki/SoundCloudOasis/OneWidget";
  private static final String SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "/wiki/SoundCloudOasis/MultipleWidgets";

  private SoundCloudWidgetPageObject widget;
  private Navigate navigate;

  private void init() {
    this.widget = new SoundCloudWidgetPageObject(driver);
    this.navigate = new Navigate();
  }

  @Test(groups = "SoundCloudWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_001_isLoaded() {
    init();

    widget.create(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SoundCloudWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_002_areLoaded() {
    init();

    widget.createMultiple(SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage(SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }
}
