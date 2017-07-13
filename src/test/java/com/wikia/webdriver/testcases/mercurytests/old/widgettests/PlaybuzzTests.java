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
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.PlaybuzzWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;

@Test(groups = "Mercury_PlaybuzzWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.NEXUS_5X
)
public class PlaybuzzTests extends NewTestTemplate {

  private static final String PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME = "PlaybuzzMercury/OneWidget";
  private static final String PLAYBUZZ_MULTIPLE_WIDGETS_ARTIVLE_NAME = "PlaybuzzMercury/MultipleWidgets";
  private static final String PLAYBUZZ_INCORRECT_WIDGET_ARTICLE_NAME = "PlaybuzzMercury/IncorrectWidget";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME;

  @Test(groups = "MercuryPlaybuzzWidgetTest_001")
  public void MercuryPlaybuzzWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new PlaybuzzWidgetPageObject().create(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_002")
  public void MercuryPlaybuzzWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new PlaybuzzWidgetPageObject().create(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPageByPath(MercurySubpages.MAIN_PAGE);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_003")
  public void MercuryPlaybuzzWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new PlaybuzzWidgetPageObject().createMultiple(PLAYBUZZ_MULTIPLE_WIDGETS_ARTIVLE_NAME);
    new ArticleContent().push("Playbuzz Mercury Widget 003", "Map");

    new Navigate().toPage(PLAYBUZZ_ONE_WIDGET_ARTICLE_NAME);
    new TopBar().openSearch().navigateToPage(QUERY_1);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_004")
  public void MercuryPlaybuzzWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new PlaybuzzWidgetPageObject().createMultiple(PLAYBUZZ_MULTIPLE_WIDGETS_ARTIVLE_NAME);

    new Navigate().toPage(PLAYBUZZ_MULTIPLE_WIDGETS_ARTIVLE_NAME);

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryPlaybuzzWidgetTest_005")
  public void MercuryPlaybuzzWidgetTest_005_isErrorPresent() {
    WidgetPageObject widget =
            new PlaybuzzWidgetPageObject().createIncorrect(PLAYBUZZ_INCORRECT_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(PLAYBUZZ_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
