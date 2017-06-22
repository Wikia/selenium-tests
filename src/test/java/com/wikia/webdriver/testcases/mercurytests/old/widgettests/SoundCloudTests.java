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
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.SoundCloudWidgetPageObject;

import org.testng.annotations.Test;
@Test(groups = "Mercury_SoundCloudWdiget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class SoundCloudTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private SoundCloudWidgetPageObject widget;
  private Navigate navigate;

  private static final String SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME = "SoundCloudMercury/OneWidget";
  private static final String SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME = "SoundCloudMercury/MultipleWidgets";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME;
  private static final String
          VALID_SOUNDCLOUD_TAG = "<soundcloud width=\"100%\" height=\"166\" scrolling=\"no\" frameborder=\"no\" url=\"https://api.soundcloud.com/tracks/34019569\" color=\"0066cc\" />";

  private void init() {
    this.topBar = new TopBar();
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate();
    this.widget = new SoundCloudWidgetPageObject();
  }

  @Test(groups = "MercurySoundCloudWidgetTest_001")
  public void MercurySoundCloudWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_SOUNDCLOUD_TAG, SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage("/" + SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundCloudWidgetTest_002")
  public void MercurySoundCloudWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_SOUNDCLOUD_TAG, SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    init();

    navigate.toPage("/" + SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundCloudWidgetTest_003")
  public void MercurySoundCloudWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push(VALID_SOUNDCLOUD_TAG, SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("SoundCloud test 003", "Map");
    init();

    navigate.toPage("/" + SOUND_CLOUD_ONE_WIDGET_ARTICLE_NAME);
    topBar.openSearch().navigateToPage(QUERY_1);
    topBar.openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercurySoundcloudWidgetTest_004")
  public void MercurySoundcloudWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticleContent().push(VALID_SOUNDCLOUD_TAG + " " + VALID_SOUNDCLOUD_TAG,
            SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME);
    init();

    navigate.toPage("/" + SOUND_CLOUD_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }
}
