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
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.elements.mercury.pages.discussions.ReportedPostsAndRepliesPage;

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

  @Test(groups = "discussions-anonUserOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void anonUserOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    userSeeProperMessageWhenUploadsEmptyPostDetailsPage();
  }


  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    userSeeProperMessageWhenUploadsEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-loggedInUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    userSeeProperMessageWhenUploadsEmptyPostDetailsPage();
  }

  /**
   * STAFF USERS ON DESKTOP SECTION
   */

  @Test(groups = "discussions-anonUserOnDesktopCanSeePostDetailsList")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    userSeeProperMessageWhenUploadsEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-anonUserOnDesktopCanSeePostDetailsList")
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.FIREFOX, browserSize = DESKTOP_RESOLUTION)
  public void staffUserOnDesktopSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    userSeeProperMessageWhenUploadsEmptyPostDetailsPage();
  }

  /**
   * STAFF USERS ON MOBILE SECTION
   */

  @Test(groups = "discussions-loggedInUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    userSeeProperMessageWhenUploadsEmptyReportedPostsPage();
  }

  @Test(groups = "discussions-loggedInUserOnMobileCanSeePostDetailsList")
  @Execute(asUser = User.STAFF)
  @InBrowser(
      browser = Browser.CHROME,
      emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    userSeeProperMessageWhenUploadsEmptyPostDetailsPage();
  }

  /**
   * TESTING METHODS SECTION
   */

  private void userSeeProperMessageWhenUploadsNonExistingUserPostsPage() {

  }

  private void userSeeProperMessageWhenUploadsEmptyReportedPostsPage() {
    ErrorMessages errorMessage = new ReportedPostsAndRepliesPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isErrorMessagePresent());

  }

  private void userSeeProperMessageWhenUploadsEmptyPostDetailsPage() {
    ErrorMessages errorMessages = new PostsListPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessages.isEmptyPostsListMessageDisplayed());
    Assertion.assertEquals(
        errorMessages.getEmptyPostsListMessageText(),
        "No posts yet. Get the discussion started, create the first post now!");
  }

  private void userSeeProperMessageWhenUploadsEmptyPostsListPage() {

  }
}

