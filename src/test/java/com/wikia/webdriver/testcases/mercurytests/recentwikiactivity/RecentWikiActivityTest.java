package com.wikia.webdriver.testcases.mercurytests.recentwikiactivity;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
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
public class RecentWikiActivityTest extends NewTestTemplate {

  @Test()
  public void recentWikiActivity_anonNavigateToRWAfromMenu() {
    new MainPage()
        .open()
        .getTopBar()
        .openNavigation()
        .openRecentWikiActivity();
  }

  @Test()
  public void recentWikiActivity_anonOpenDiffPageAndGoBack() {
    this.openDiffPageAndGoBack();
  }

  @Test()
  @Execute(asUser = User.CONSTANTLY_BLOCKED_USER)
  public void recentWikiActivity_blockedUserOpenDiffPageAndGoBack() {
    this.openDiffPageAndGoBack();
  }

  @Test()
  @Execute(asUser = User.USER)
  public void recentWikiActivityAsUser_undoWithoutSummary() {
    this.openDiffPage()
        .submitWithoutSummary()
        .displaySuccessNotification();
  }

  @Test()
  @Execute(asUser = User.USER)
  public void recentWikiActivityAsUser_undoWithSummary() {
    this.openDiffPage()
        .submitWithSummary()
        .displaySuccessNotification();
  }

  @Test()
  @Execute(asUser = User.USER)
  public void recentWikiActivityAsUser_goBackFromSummaryWithoutUndo() {
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
