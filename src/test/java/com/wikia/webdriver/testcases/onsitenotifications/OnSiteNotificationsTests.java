package com.wikia.webdriver.testcases.onsitenotifications;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsOperations;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyEntity;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.NotificationFactory;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notifications;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = "on-site-notifications")
public class OnSiteNotificationsTests extends NewTestTemplate {

  private String siteId;
  private static final String wikiName = MercuryWikis.DISCUSSIONS_3;

  @BeforeClass
  public void setUp() {
    siteId = Utils.excractSiteIdFromWikiName(wikiName);
  }

  /**
   * Test methods - DESKTOP
   */

  @Execute(asUser = User.USER)
  public void userOnDesktopReceivesPostReplyNotification() {
    Notification notification = createPostReplyNotification(User.USER, User.USER_2);

    Assertion.assertTrue(getNotificationsDesktop().contains(notification));
  }

  @Execute(asUser = User.USER)
  public void userOnDesktopReceivesPostUpvoteNotification() {
    Notification notification = createPostUpvoteNotification(User.USER, User.USER_2);

    Assertion.assertTrue(getNotificationsDesktop().contains(notification));
  }

  @Execute(asUser = User.USER)
  public void userOnDesktopReceivesReplyUpvoteNotification() {
    Notification notification = createReplyUpvoteNotification(User.USER, User.USER, User.USER_2);

    Assertion.assertTrue(getNotificationsDesktop().contains(notification));
  }

  /**
   * Test methods - MOBILE
   */

  @Execute(asUser =  User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileReceivesPostReplyNotification() {
    Notification notification = createPostReplyNotification(User.USER, User.USER_2);

    Assertion.assertTrue(getNotificationsMobile().contains(notification));
  }

  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileReceivesPostUpvoteNotification() {
    Notification notification = createPostUpvoteNotification(User.USER, User.USER_2);

    Assertion.assertTrue(getNotificationsMobile().contains(notification));
  }

  @Execute(asUser = User.USER)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  public void userOnMobileReceivesReplyUpvoteNotification() {
    Notification notification = createReplyUpvoteNotification(User.USER, User.USER, User.USER_2);

    Assertion.assertTrue(getNotificationsMobile().contains(notification));
  }


  /**
   * Helper methods
   */

  private PostEntity.Data createPostAs(User user) {
    return DiscussionsOperations.using(user, driver).createPostWithUniqueData(siteId);
  }

  private ReplyEntity.Data createReplyToPostAs(PostEntity.Data post, User user) {
    return DiscussionsOperations.using(user, driver).createReplyToPost(siteId, post);
  }

  private void upvotePostAs(PostEntity.Data post, User user) {
    DiscussionsOperations.using(user, driver).upvotePost(siteId, post);
  }

  private void upvoteReplyAs(ReplyEntity.Data reply, User user) {
    DiscussionsOperations.using(user, driver).upvoteReply(siteId, reply);
  }

  private Notification createPostReplyNotification(User postAuthor, User replyAuthor) {
    PostEntity.Data post = createPostAs(postAuthor);
    createReplyToPostAs(post, replyAuthor);
    return NotificationFactory.getPostReplyNotification(replyAuthor, post);
  }

  private Notification createPostUpvoteNotification(User postAuthor, User upvoteAuthor) {
    PostEntity.Data post = createPostAs(postAuthor);
    upvotePostAs(post, upvoteAuthor);
    return NotificationFactory.getPostUpvoteNotification(post);
  }

  private Notification createReplyUpvoteNotification(User postAuthor, User replyAuthor, User upvoteAuthor) {
    PostEntity.Data post = createPostAs(postAuthor);
    ReplyEntity.Data reply = createReplyToPostAs(post, replyAuthor);
    upvoteReplyAs(reply, upvoteAuthor);
    return NotificationFactory.getReplyUpvoteNotification();
  }

  private Notifications getNotificationsDesktop() {
    return new PostsListPage()
      .open(siteId)
      .getNotificationsDropdown()
      .expand()
      .getNotifications();
  }

  private Notifications getNotificationsMobile() {
    return new PostsListPage()
      .open(siteId)
      .getTopBar()
      .openNavigation()
      .openUserProfile()
      .getNotifications();
  }

}
