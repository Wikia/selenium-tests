package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.helpers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.mercury.Navigation;
import com.wikia.webdriver.elements.mercury.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.VKWidgetPageObject;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)
public class VKTests extends NewTestTemplate {

  private TopBar topBar;
  private Navigation navigation;
  private Navigate navigate;
  private VKWidgetPageObject widget;

  private static final String VK_ONE_WIDGET_ARTICLE_NAME = "VKMercury/OneWidget";
  private static final String VK_MULTIPLE_WIDGETS_ARTICLE_NAME = "VKMercury/MultipleWidgets";
  private static final String VK_INCORRECT_WIDGET_ARTICLE_NAME = "VKMercury/IncorrectWidget";
  private static final String MAPS_ARTICLE_NAME = "Map";

  private void init() {
    this.topBar = new TopBar(driver);
    this.navigation = new Navigation(driver);
    this.navigate = new Navigate(driver);
    this.widget = new VKWidgetPageObject(driver);
  }

  @Test(groups = "MercuryVKWidgetTest_001")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.create(VK_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + VK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_002")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    init();

    widget.create(VK_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + MercurySubpages.MAIN_PAGE);
    topBar.openNavigation();
    navigation.navigateToPage(VK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_003")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    init();

    widget.create(VK_ONE_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + VK_ONE_WIDGET_ARTICLE_NAME);
    topBar.openNavigation();
    navigation.navigateToPage(MAPS_ARTICLE_NAME);
    topBar.openNavigation();
    navigation.navigateToPage(VK_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_004")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    init();

    widget.createMultiple(VK_MULTIPLE_WIDGETS_ARTICLE_NAME);
    navigate.toPage("/wiki/" + VK_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(
        widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG
    );

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryVKWidgetTest_005")
  @Execute(asUser = User.USER)
  public void MercuryVKWidgetTest_005_isErrorPresent() {
    init();

    widget.createIncorrect(VK_INCORRECT_WIDGET_ARTICLE_NAME);
    navigate.toPage("/wiki/" + VK_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
