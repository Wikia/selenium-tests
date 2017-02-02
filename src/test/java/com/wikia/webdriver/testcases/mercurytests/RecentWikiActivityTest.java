package com.wikia.webdriver.testcases.mercurytests;

import com.wikia.webdriver.common.core.annotations.DontRun;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.RecentWikiActivity;
import com.wikia.webdriver.elements.mercury.pages.MainPage;
import com.wikia.webdriver.elements.mercury.pages.RecentWikiActivityPage;
import org.testng.annotations.Test;

@Execute(onWikia = "aga")
@InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
@Test(groups = "Mercury_RecentWikiActivity")
public class RecentWikiActivityTest extends NewTestTemplate {

  @Test(groups = "mercury_recentWikiActivity_anonNavigateToRWAfromMenu")
  @Execute(mockAds = "true")
  public void mercury_recentWikiActivity_anonNavigateToRWAfromMenu() {
    new MainPage()
        .open()
        .getTopBar()
        .openNavigation()
        .openRecentWikiActivity();
  }

  @Test(groups = "mercury_recentWikiActivity_anonOpenDiffPageAndGoBack")
  @RelatedIssue(issueID = "WW-465")
  @DontRun(env = {"preview", "prod"})
  public void mercury_recentWikiActivity_anonOpenDiffPageAndGoBack() {
    this.openDiffPageAndGoBack();
  }

  @Test(groups = "mercury_recentWikiActivity_blockedUserOpenDiffPageAndGoBack")
  @Execute(asUser = User.CONSTANTLY_BLOCKED_USER)
  @RelatedIssue(issueID = "WW-465")
  @DontRun(env = {"preview", "prod"})
  public void mercury_recentWikiActivity_blockedUserOpenDiffPageAndGoBack() {
    this.openDiffPageAndGoBack();
  }

  @Test(groups = "mercury_recentWikiActivity_loggedUserOpenDiffPageAndSubmitWithoutSummary")
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "WW-465")
  @DontRun(env = {"preview", "prod"})
  public void mercury_recentWikiActivity_loggedUserOpenDiffPageAndSubmitWithoutSummary() {
    this.openDiffPage()
        .submitWithoutSummary()
        .displaySuccessNotification();
  }

  @Test(groups = "mercury_recentWikiActivity_loggedUserOpenDiffPageAndSubmitWithSummary")
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "WW-465")
  @DontRun(env = {"preview", "prod"})
  public void mercury_recentWikiActivity_loggedUserOpenDiffPageAndSubmitWithSummary() {
    this.openDiffPage()
        .submitWithSummary()
        .displaySuccessNotification();
  }

  @Test(groups = "mercury_recentWikiActivity_loggedUserOpenDiffPageAndGoBackFromSummary")
  @Execute(asUser = User.USER)
  @RelatedIssue(issueID = "WW-465")
  @DontRun(env = {"preview", "prod"})
  public void mercury_recentWikiActivity_loggedUserOpenDiffPageAndGoBackFromSummary() {
    this.openDiffPage()
        .goBackFromSummaryPage();
  }

  private void openDiffPageAndGoBack() {
    this.openDiffPage()
        .undoButtonNotVisible()
        .goBackToRWA();
  }

  private RecentWikiActivity openDiffPage() {
    return new RecentWikiActivityPage()
        .open()
        .getRecentWikiActivity()
        .openDiffPage();
  }
}
