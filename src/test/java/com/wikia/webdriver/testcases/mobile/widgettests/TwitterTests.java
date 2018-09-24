package com.wikia.webdriver.testcases.mobile.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MobileSubpages;
import com.wikia.webdriver.common.contentpatterns.MobileWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.Navigate;
import com.wikia.webdriver.elements.communities.mobile.components.navigation.global.GlobalNavigationMobile;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.TwitterWidgetPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.WidgetPageObject;
import org.testng.annotations.Test;
@Test(groups = "Mercury_TwitterWidget")
@Execute(onWikia = MobileWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.GOOGLE_NEXUS_5
)

public class TwitterTests extends NewTestTemplate {

  private static final String TWITTER_ONE_WIDGET_ARTICLE_NAME = "TwitterMercury/OneWidget";
  private static final String TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME = "TwitterMercury/MultipleWidgets";
  private static final String TWITTER_INCORRECT_WIDGET_ARTICLE_NAME = "TwitterMercury/IncorrectWidget";
  private static final String QUERY_1 = MobileSubpages.MAP;
  private static final String QUERY_2 = TWITTER_ONE_WIDGET_ARTICLE_NAME;

  @Test(enabled = false, groups = "MercuryTwitterWidgetTest_001")
  @RelatedIssue(issueID = "MAIN-17401")
  public void MercuryTwitterWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new TwitterWidgetPageObject().create(TWITTER_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(TWITTER_ONE_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(enabled = false, groups = "MercuryTwitterWidgetTest_002")
  @RelatedIssue(issueID = "MAIN-17401")
  public void MercuryTwitterWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new TwitterWidgetPageObject().create(TWITTER_ONE_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(MobileSubpages.MAIN_PAGE);
    new GlobalNavigationMobile().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(enabled = false, groups = "MercuryTwitterWidgetTest_003")
  @RelatedIssue(issueID = "MAIN-17401")
  public void MercuryTwitterWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    WidgetPageObject widget =
            new TwitterWidgetPageObject().create(TWITTER_ONE_WIDGET_ARTICLE_NAME);
    new ArticleContent().push("Twitter test 003", "Map");

    new Navigate().toPage(TWITTER_ONE_WIDGET_ARTICLE_NAME);
    new GlobalNavigationMobile().openSearch().navigateToPage(QUERY_1);
    new GlobalNavigationMobile().openSearch().navigateToPage(QUERY_2);

    Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(enabled = false, groups = "MercuryTwitterWidgetTest_004")
  @RelatedIssue(issueID = "MAIN-17401")
  public void MercuryTwitterWidgetTest_004_areLoadedOnFirstVisitDirectlyFromUrl() {
    WidgetPageObject widget =
            new TwitterWidgetPageObject().createMultiple(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);

    new Navigate().toPage(TWITTER_MULTIPLE_WIDGETS_ARTICLE_NAME);

    Assertion.assertTrue(
        widget.areAllValidSwappedForIFrames(),
        MercuryMessages.SOME_VALID_WIDGETS_WERE_NOT_SWAPPED_MSG
    );

    Assertion.assertTrue(widget.areLoaded(), MercuryMessages.INVISIBLE_MSG);
  }

  @Test(groups = "MercuryTwitterWidgetTest_005")
  public void MercuryTwitterWidgetTest_005_isErrorPresent() {
    WidgetPageObject widget =
            new TwitterWidgetPageObject().createIncorrect(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);

    new Navigate().toPage(TWITTER_INCORRECT_WIDGET_ARTICLE_NAME);

    Assertion.assertTrue(widget.isErrorPresent(), MercuryMessages.INVISIBLE_MSG);
  }
}
