package com.wikia.webdriver.testcases.mobilewikitests.widgettests;

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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;
@Test(groups = "Mercury_SoundCloudWdiget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class SoundCloudTests extends NewTestTemplate {

  private static final String SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME = "SoundCloudMercury/OneWidget";
  private static final String SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME = "SoundCloudMercury/MultipleWidgets";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME;

  @Test(groups = "MercurySoundCloudWidgetTest_001")
  public void MercurySoundCloudWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new SoundCloudWidgetPageObject().create(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundCloudWidgetTest_002")
  public void MercurySoundCloudWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new SoundCloudWidgetPageObject().create(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundCloudWidgetTest_003")
  public void MercurySoundCloudWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new SoundCloudWidgetPageObject().create(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("SoundCloud test 003", "Map");

    new Navigate().toPage(SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    new TopBar().openSearch().navigateToPage(QUERY_1);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundcloudWidgetTest_004")
  public void MercurySoundcloudWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new SoundCloudWidgetPageObject().createMultiple(SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME);


    new Navigate().toPage(SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }
}
