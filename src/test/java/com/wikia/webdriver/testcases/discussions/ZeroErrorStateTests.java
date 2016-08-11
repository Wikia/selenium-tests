package com.wikia.webdriver.testcases.discussions;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
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

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopSeeProperMessageWhenUploadsEmptyReportedPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    userSeeProperMessageWhenUploadsEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-anonUserOnDesktopSeeProperMessageWhenUploadsEmptyPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopSeeProperMessageWhenUploadsEmptyPostsListPage() {
    userSeeProperMessageWhenUploadsEmptyPostsListPage();
  }

  @Test(groups = "discussions-anonOnDesktopSeeProperMessageWhenUploadsNonExistingUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonOnDesktopSeeProperMessageWhenUploadsNonExistingUserPostsPage() {
    userOnDesktopSeeProperMessageWhenUploadsNonExistingUserPostsPage();
  }

  @Test(groups = "discussions-anonOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    userOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage();
  }


  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-anonUserOnMobileSeeProperMessageWhenUploadsEmptyReportedPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    userSeeProperMessageWhenUploadsEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-anonUserOnMobileSeeProperMessageWhenUploadsEmptyPostsListPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileSeeProperMessageWhenUploadsEmptyPostsListPage() {
    userSeeProperMessageWhenUploadsEmptyPostsListPage();
  }

  @Test(groups = "discussions-anonOnMobileSeeProperMessageWhenUploadsNonExistingUserPostsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonOnMobileSeeProperMessageWhenUploadsNonExistingUserPostsPage() {
    userOnMobileSeeProperMessageWhenUploadsNonExistingUserPostsPage();
  }

  @Test(groups = "discussions-anonOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    userOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage();
  }


  /**
   * STAFF USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-staffUserOnDesktopSeeProperMessageWhenUploadsEmptyReportedPostsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    userSeeProperMessageWhenUploadsEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-staffUserOnDesktopSeeProperMessageWhenUploadsEmptyPostsListPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeeProperMessageWhenUploadsEmptyPostsListPage() {
    userSeeProperMessageWhenUploadsEmptyPostsListPage();
  }

  @Test(groups = "discussions-staffUserOnDesktopSeeProperMessageWhenUploadsNonExistingUserPostsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeeProperMessageWhenUploadsNonExistingUserPostsPage() {
    userOnDesktopSeeProperMessageWhenUploadsNonExistingUserPostsPage();
  }

  @Test(groups = "discussions-staffUserOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    userOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage();
  }

  /**
   * STAFF USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-staffUserOnMobileSeeProperMessageWhenUploadsEmptyReportedPostsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    userSeeProperMessageWhenUploadsEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-staffUserOnMobileSeeProperMessageWhenUploadsEmptyPostsListPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeeProperMessageWhenUploadsEmptyPostsListPage() {
    userSeeProperMessageWhenUploadsEmptyPostsListPage();
  }

  @Test(groups = "discussions-staffUserOnMobileSeeProperMessageWhenUploadsNonExistingUserPostsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeeProperMessageWhenUploadsNonExistingUserPostsPage() {
    userOnMobileSeeProperMessageWhenUploadsNonExistingUserPostsPage();
  }

  @Test(groups = "discussions-staffUserOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    userOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void userOnDesktopSeeProperMessageWhenUploadsNonExistingUserPostsPage() {
    ErrorMessages errorMessage = new UserPostsPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(
        errorMessage.getErrorMessageText(),
        "Uh oh, looks like this page doesn't exist!\n"
        + "Show Me All Discussions");
  }

  private void userOnMobileSeeProperMessageWhenUploadsNonExistingUserPostsPage() {
    ErrorMessages errorMessage = new UserPostsPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(
        errorMessage.getErrorMessageText(),
        "Uh oh, looks like this page doesn't exist!\n"
        + "All Discussions");
  }

  private void userSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    ErrorMessages errorMessage = new ReportedPostsAndRepliesPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(
        errorMessage.getErrorMessageText(),
        "There are no reported posts or replies.\n"
        + "Show Me All Discussions");

  }

  private void userSeeProperMessageWhenUploadsEmptyPostsListPage() {
    ErrorMessages errorMessage = new PostsListPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isEmptyPostsListMessageDisplayed());
    Assertion.assertEquals(
        errorMessage.getEmptyPostsListMessageText(),
        "No posts yet. Get the discussion started, create the first post now!");
  }

  private void userOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    ErrorMessages errorMessage = new PostDetailsPage().openEmpyPost().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(
        errorMessage.getErrorMessageText(),
        "Uh oh, looks like this page doesn't exist!\n"
        + "Show Me All Discussions");
  }

  private void userOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    ErrorMessages errorMessage = new PostDetailsPage().openEmpyPost().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());
    Assertion.assertEquals(
        errorMessage.getErrorMessageText(),
        "Uh oh, looks like this page doesn't exist!\n"
        + "All Discussions");
  }
}

