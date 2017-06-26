package com.wikia.webdriver.testcases.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "SoundCloudWidget")
@InBrowser(browser = Browser.CHROME)
public class SoundCloudTests extends NewTestTemplate {

  private static final String SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME =
      "SoundCloudOasis_OneWidget";
  private static final String SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME =
      "SoundCloudOasis_MultipleWidgets";

  @Test(groups = "SoundCloudWidgetTest_001")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_001_isLoaded() {
    WidgetPageObject widget =
            new SoundCloudWidgetPageObject().create(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    new ArticlePageObject().open(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "SoundCloudWidgetTest_002")
  @Execute(onWikia = "mercuryautomationtesting")
  public void SoundCloudWidgetTest_002_areLoaded() {
    WidgetPageObject widget =
            new SoundCloudWidgetPageObject().createMultiple(SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME);
    new ArticlePageObject().open(SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }
}
