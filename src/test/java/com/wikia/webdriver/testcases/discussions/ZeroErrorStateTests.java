package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostDetailsPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.ReportedPostsAndRepliesPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.UserPostsPage;

import org.testng.annotations.Test;

@Execute(onWikia = MercuryWikis.DISCUSSIONS_MESSAGING)
@Test(groups = {"discussions-zero-error-state"})

public class ZeroErrorStateTests extends NewTestTemplate {

  private static final String DESKTOP_RESOLUTION = "1920x1080";
  private static final String MESSAGE_1 = "Uh oh, looks like this page doesn't exist!\n";
  private static final String MESSAGE_2 = "Show Me All Discussions";
  private static final String MESSAGE_3 = "All Discussions";

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(enabled = false, groups = "discussions-anonUserOnDesktopSeesProperMessageWhenOpensEmptyReportedPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  @RelatedIssue(issueID = "SOC-3667")
  public void anonUserOnDesktopSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    userSeesProperMessageWhenOpensEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-anonUserOnDesktopSeesProperMessageWhenOpensEmptyPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopSeesProperMessageWhenOpensEmptyPostsListPage() {
    userSeesProperMessageWhenOpensEmptyPostsListPage();
  }

  @Test(enabled = false, groups = "discussions-anonOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  @RelatedIssue(issueID = "SOC-3667")
  public void anonOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    userOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage();
  }

  @Test(groups = "discussions-anonOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    userOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage();
  }


  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileSeesProperMessageWhenOpensEmptyReportedPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    userSeesProperMessageWhenOpensEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-anonUserOnMobileSeesProperMessageWhenOpensEmptyPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileSeesProperMessageWhenOpensEmptyPostsListPage() {
    userSeesProperMessageWhenOpensEmptyPostsListPage();
  }

  @Test(groups = "discussions-anonOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    userOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage();
  }

  @Test(groups = "discussions-anonOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    userOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage();
  }


  /**
   * STAFF USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-staffUserOnDesktopSeesProperMessageWhenOpensEmptyReportedPostsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    userSeesProperMessageWhenOpensEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-staffUserOnDesktopSeesProperMessageWhenOpensEmptyPostsListPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeesProperMessageWhenOpensEmptyPostsListPage() {
    userSeesProperMessageWhenOpensEmptyPostsListPage();
  }

  @Test(groups = "discussions-staffUserOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    userOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage();
  }

  @Test(groups = "discussions-staffUserOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    userOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage();
  }

  /**
   * STAFF USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-staffUserOnMobileSeesProperMessageWhenOpensEmptyReportedPostsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    userSeesProperMessageWhenOpensEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-staffUserOnMobileSeesProperMessageWhenOpensEmptyPostsListPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensEmptyPostsListPage() {
    userSeesProperMessageWhenOpensEmptyPostsListPage();
  }

  @Test(groups = "discussions-staffUserOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    userOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage();
  }

  @Test(groups = "discussions-staffUserOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    userOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void userOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    ErrorMessages errorMessage = new UserPostsPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(errorMessage.getErrorMessageText(), MESSAGE_1 + MESSAGE_2);
  }

  private void userOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    ErrorMessages errorMessage = new UserPostsPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(errorMessage.getErrorMessageText(), MESSAGE_1 + MESSAGE_3);
  }

  private void userSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    ErrorMessages errorMessage = new ReportedPostsAndRepliesPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(
        errorMessage.getErrorMessageText(),
        "There are no reported posts or replies.\n" + MESSAGE_2);
  }

  private void userSeesProperMessageWhenOpensEmptyPostsListPage() {
    ErrorMessages errorMessage = new PostsListPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isEmptyPostsListMessageDisplayed());
    Assertion.assertEquals(
        errorMessage.getEmptyPostsListMessageText(),
        "No posts yet. Get the discussion started, create the first post now!");
  }

  private void userOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    ErrorMessages errorMessage = new PostDetailsPage().openEmptyPost().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(errorMessage.getErrorMessageText(), MESSAGE_1 + MESSAGE_2);
  }

  private void userOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    ErrorMessages errorMessage = new PostDetailsPage().openEmptyPost().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(errorMessage.getErrorMessageText(), MESSAGE_1 + MESSAGE_3);
  }
}

