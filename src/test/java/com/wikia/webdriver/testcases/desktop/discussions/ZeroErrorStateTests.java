package com.wikia.webdriver.testcases.desktop.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.NoFollowedPostsMessage;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.Replies;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.*;

import org.testng.annotations.Test;

@Execute(onWikia = "deemptydiscussions", language = "de")
@Test(groups = {"discussions-zero-error-state"})
public class ZeroErrorStateTests extends NewTestTemplate {

  private static final String MESSAGE_1 = "Die von dir angeforderte Seite existiert nicht.\n";
  private static final String MESSAGE_2 = "Alle Diskussionen anzeigen";
  private static final String MESSAGE_3 = "Alle Diskussionen";

  private static final String NO_REPLIES_MESSAGE = "Noch keine Antworten. Deine kann die erste sein!";
  private static final String FOLLOW_MESSAGE_HEADER_TEXT = "Willkommen auf dem Tab „Verfolgt“.";
  private static final String
      FOLLOW_MESSAGE_CONTENT_TEXT
      = "Klicke auf das Symbol „Folgen“ am Ende eines beliebigen Beitrags, um deine Liste mit den Diskussionen zu füllen, die dir am wichtigsten sind. Wir stellen sie hier ein und geben dir Bescheid, wenn es etwas Neues gibt.";
  private static final String FOLLOW_MESSAGE_BUTTON_TEXT = "FINDE BEITRÄGE, DENEN DU FOLGEN MÖCHTEST";
  private static final String NO_REPLIES_ICON_MESSAGE = "No replies icon should be visible.";
  private static final String
      NO_REPLIES_UNDER_POST_MESSAGE
      = "There should be no replies on new post (without replies).";
  private static final String NO_POSTS_MESSAGE = "Es gibt noch keine Beiträge. Bringe doch du die Diskussion zum Laufen und verfasse jetzt den ersten Beitrag!";
  private static final String NO_REPORTED_POSTS_MESSAGE = "Es gibt keine gemeldeten Beiträge oder Antworten.\n";

  private static final String DESKTOP = "discussions-zero-error-state-desktop";
  private static final String MOBILE = "discussions-zero-error-state-mobile";

  /**
   * ANONS ON DESKTOP SECTION
   */

  @Test(groups = DESKTOP, enabled = false)
  @RelatedIssue(issueID = "SOC-3667")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    userSeesProperMessageWhenOpensEmptyReportedPostsPage();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonUserOnDesktopSeesProperMessageWhenOpensEmptyPostsListPage() {
    userSeesProperMessageWhenOpensEmptyPostsListPage();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    userOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    userOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.ANONYMOUS, onWikia = "qadiscussions", language = "de")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void anonOnDesktopSeesProperMessageWhenOpensPostDetailsPageWithoutReplies() {
    userSeesProperMessageWhenOpensPostDetailsPageWithoutReplies();
  }

  /**
   * ANONS ON MOBILE SECTION
   */

  @Test(groups = MOBILE, enabled = false)
  @RelatedIssue(issueID = "SOC-3667")
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    userSeesProperMessageWhenOpensEmptyReportedPostsPage();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonUserOnMobileSeesProperMessageWhenOpensEmptyPostsListPage() {
    userSeesProperMessageWhenOpensEmptyPostsListPage();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    userOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    userOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.ANONYMOUS, onWikia = "qadiscussions", language = "de")
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void anonOnMobileSeesProperMessageWhenOpensPostDetailsPageWithoutReplies() {
    userSeesProperMessageWhenOpensPostDetailsPageWithoutReplies();
  }

  /**
   * STAFF USERS ON DESKTOP SECTION
   */

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserOnDesktopSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    userSeesProperMessageWhenOpensEmptyReportedPostsPage();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserOnDesktopSeesProperMessageWhenOpensEmptyPostsListPage() {
    userSeesProperMessageWhenOpensEmptyPostsListPage();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    userOnDesktopSeesProperMessageWhenOpensNonExistingUserPostsPage();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    userOnDesktopSeesProperMessageWhenOpensEmptyPostDetailsPage();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF, onWikia = "qadiscussions", language = "de")
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserOnDesktopSeesProperMessageWhenOpensPostDetailsPageWithoutReplies() {
    userSeesProperMessageWhenOpensPostDetailsPageWithoutReplies();
  }

  @Test(groups = DESKTOP)
  @Execute(asUser = User.STAFF)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void staffUserOnDesktopSeesProperMessageWhenOpensEmptyFollowPage() {
    userSeesProperMessageWhenOpensEmptyFollowPage();
  }

  /**
   * STAFF USERS ON MOBILE SECTION
   */

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensEmptyReportedPostsPage() {
    userSeesProperMessageWhenOpensEmptyReportedPostsPage();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensEmptyPostsListPage() {
    userSeesProperMessageWhenOpensEmptyPostsListPage();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage() {
    userOnMobileSeesProperMessageWhenOpensNonExistingUserPostsPage();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage() {
    userOnMobileSeesProperMessageWhenOpensEmptyPostDetailsPage();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF, onWikia = "qadiscussions", language = "de")
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensPostDetailsPageWithoutReplies() {
    userSeesProperMessageWhenOpensPostDetailsPageWithoutReplies();
  }

  @Test(groups = MOBILE)
  @Execute(asUser = User.STAFF)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void staffUserOnMobileSeesProperMessageWhenOpensEmptyFollowPage() {
    userSeesProperMessageWhenOpensEmptyFollowPage();
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
        NO_REPORTED_POSTS_MESSAGE + MESSAGE_2
    );
  }

  private void userSeesProperMessageWhenOpensEmptyPostsListPage() {
    ErrorMessages errorMessage = new PostsListPage().open().getErrorMessages();
    Assertion.assertTrue(errorMessage.isEmptyPostsListMessageDisplayed());
    Assertion.assertEquals(
        errorMessage.getEmptyPostsListMessageText(),
        NO_POSTS_MESSAGE
    );
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

  private void userSeesProperMessageWhenOpensPostDetailsPageWithoutReplies() {
    final String postId = DiscussionsClient.using(User.USER, driver)
        .createPostWithUniqueData()
        .getId();

    final Replies replies = new PostDetailsPage().open(postId).getReplies();

    Assertion.assertTrue(replies.isEmpty(), NO_REPLIES_UNDER_POST_MESSAGE);
    Assertion.assertTrue(replies.hasNoRepliesIcon(), NO_REPLIES_ICON_MESSAGE);
    Assertion.assertEquals(replies.getNoRepliesMessage(), NO_REPLIES_MESSAGE);
  }

  private void userSeesProperMessageWhenOpensEmptyFollowPage() {
    NoFollowedPostsMessage noFollowedPostsMessage = new FollowPage().open()
        .getNoFollowedPostsMessage();

    Assertion.assertEquals(noFollowedPostsMessage.getHeaderText(), FOLLOW_MESSAGE_HEADER_TEXT);
    Assertion.assertEquals(noFollowedPostsMessage.getContentText(), FOLLOW_MESSAGE_CONTENT_TEXT);
    Assertion.assertEquals(noFollowedPostsMessage.getButtonText(), FOLLOW_MESSAGE_BUTTON_TEXT);
  }
}

