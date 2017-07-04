package com.wikia.webdriver.testcases.mercurytests.old.widgettests;

import com.wikia.webdriver.common.contentpatterns.MercuryMessages;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import com.wikia.webdriver.pageobjectsfactory.pageobject.HomePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.widget.*;
import org.joda.time.DateTime;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Test(groups = "Mercury_AllTagsWidget")
@Execute(onWikia = MercuryWikis.MERCURY_AUTOMATION_TESTING)
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class AllTagsTests extends NewTestTemplate {

  private static final String ARTICLE_NAME = "AllTagsWidgetMercury";
  private static final String QUERY_1 = MercurySubpages.MAP.substring(6);
  private static final String QUERY_2 = ARTICLE_NAME.substring(0, 6);
  private static List<WidgetPageObject> widgets;

  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
    widgets = new ArrayList<>();
    widgets.add(new PollsnackWidgetPageObject());
    widgets.add(new SoundCloudWidgetPageObject());
    widgets.add(new SpotifyWidgetPageObject());
    widgets.add(new TwitterWidgetPageObject());
    widgets.add(new VKWidgetPageObject());
    widgets.add(new WeiboWidgetPageObject());
    widgets.add(new GoogleFormWidgetPageObject());
    widgets.add(new PolldaddyWidgetPageObject());
    widgets.add(new PlaybuzzWidgetPageObject());
    widgets.add(new ApesterWidgetPageObject());

    String content = ">>> " + DateTime.now().getMillis() + " <<<";

    for (WidgetPageObject widget : widgets) {
      content += widget.getSingleTag();
    }

    new ArticleContent().push(content, ARTICLE_NAME);
  }

  @Test(groups = "MercuryAllTagsWidgetTest_001")
  public void MercuryAllTagsWidgetTest_001_isLoadedOnFirstVisitDirectlyFromUrl() {
    new ArticlePageObject().open(ARTICLE_NAME);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }

  @Test(groups = "MercuryAllTagsWidgetTest_002", enabled = false)
  @RelatedIssue(issueID = "XW-3652")
  public void MercuryAllTagsWidgetTest_002_isLoadedOnFirstVisitFromDifferentArticle() {
    new HomePage().open();

    new TopBar().openSearch().navigateToPage(QUERY_2);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }

  @Test(groups = "MercuryAllTagsWidgetTest_003", enabled = false)
  @RelatedIssue(issueID = "XW-3652")
  public void MercuryAllTagsWidgetTest_003_isLoadedOnSecondVisitFromDifferentArticle() {
    new ArticleContent().push("Mercury AllTags 003", "Map");
    new ArticlePageObject().open(ARTICLE_NAME);

    new TopBar().openSearch().navigateToPage(QUERY_1);
    new TopBar().openSearch().navigateToPage(QUERY_2);

    for (WidgetPageObject widget : widgets) {
      Assertion.assertTrue(widget.isLoaded(), MercuryMessages.INVISIBLE_MSG);
    }
  }
}
