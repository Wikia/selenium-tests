package com.wikia.webdriver.testcases.mercurytests.recentwikiactivity;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.api.ArticleContent;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.RecentWikiActivityPage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

@Execute(onWikia = "aga")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
public class RecentWikiActivityTest extends NewTestTemplate {

  @FindBy(css = ".diff-page__undo")
  private WebElement undoButton;

  @Test()
  public void recentWikiActivity_openFromHamburgerMenu() {
    new RecentWikiActivityPage()
        .open()
        .getTopBar()
        .openNavigation()
        .openSubMenu(1)
        .openSubMenu(0)
        .openSubMenu(0);

    Assertion.assertTrue(driver.getCurrentUrl().contains(URLsContent.RECENT_WIKI_ACTIVITY),
                         "You were not redirected to the recent wiki activity");
  }

  @Test()
  public void recentWikiActivityAsAnon_redirectionToDiffPageAndGoingBack() {
    new ArticleContent()
        .clear("RECENTLY CHANGED")
        .push("Recent wiki activity automated test", "RECENTLY CHANGED");

    new RecentWikiActivityPage()
        .open()
        .getRecentWikiActivity()
        .openDiffPage()
        .undoButtonNotVisible()
        .goBackToRWA();
  }

  @Test()
  @Execute(asUser = User.CONSTANTLY_BLOCKED_USER)
  public void recentWikiActivityAsBlockedUser_redirectionToDiffPageAndGoingBack() {
    new RecentWikiActivityPage()
        .open()
        .getRecentWikiActivity()
        .openDiffPage()
        .undoButtonNotVisible()
        .goBackToRWA();
  }

  @Test()
  @Execute(asUser = User.USER)
  public void recentWikiActivityAsUser_undoWithoutSummary() {
    new RecentWikiActivityPage()
        .open()
        .getRecentWikiActivity()
        .openDiffPage()
        .submitWithoutSummary()
        .displaySuccessNotification();
  }

  @Test()
  @Execute(asUser = User.USER)
  public void recentWikiActivityAsUser_undoWithSummary() {
    new RecentWikiActivityPage()
        .open()
        .getRecentWikiActivity()
        .openDiffPage()
        .submitWithSummary()
        .displaySuccessNotification();
  }

  @Test()
  @Execute(asUser = User.USER)
  public void recentWikiActivityAsUse_goBackFromSummaryWithoutUndo() {
    new RecentWikiActivityPage()
        .open()
        .getRecentWikiActivity()
        .openDiffPage()
        .goBackFromSummaryPage();
  }
}
