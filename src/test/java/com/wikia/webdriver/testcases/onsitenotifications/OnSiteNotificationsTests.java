package com.wikia.webdriver.testcases.onsitenotifications;

import com.wikia.webdriver.common.contentpatterns.MercuryWikis;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.remote.Utils;
import com.wikia.webdriver.common.remote.discussions.DiscussionsClient;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.components.discussions.common.PostEntity;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ReplyEntityData;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.NotificationFactory;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notifications;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class OnSiteNotificationsTests extends NewTestTemplate {

  private static final String DESKTOP = "on-site-notifications-desktop";
  private static final String MOBILE = "on-site-notifications-mobile";

  private String siteId;
  private static final String WIKI_DESKTOP = MercuryWikis.DISCUSSIONS_3;
  private static final String WIKI_MOBILE = MercuryWikis.DISCUSSIONS_4;
  private List<User> replyUsers = Arrays.asList(
    User.USER_2,
    User.USER_3,
    User.USER_4,
    User.USER_5,
    User.USER_6,
    User.USER_9);

  @BeforeClass(groups = DESKTOP)
  public void setUpDesktop() {
    siteId = Utils.excractSiteIdFromWikiName(WIKI_DESKTOP);
  }

  @BeforeClass(groups = MOBILE)
  public void setUpMobile() {
    siteId = Utils.excractSiteIdFromWikiName(WIKI_MOBILE);
  }

  /**
   * Test methods - DESKTOP
   */

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP)
  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopReceivesPostReplyNotification() {
    Notification notification = createPostReplyNotification(User.USER_11, User.USER_2);

    Assertion.assertTrue(getNotificationsDesktop().contains(notification));
  }

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP)
  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopReceivesPostUpvoteNotification() {
    Notification notification = createPostUpvoteNotification(User.USER_11, User.USER_2);

    Assertion.assertTrue(getNotificationsDesktop().contains(notification));
  }

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP)
  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopReceivesReplyUpvoteNotification() {
    Notification notification = createReplyUpvoteNotification(User.USER_11, User.USER_11, User.USER_2);

    Assertion.assertTrue(getNotificationsDesktop().contains(notification));
  }

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP)
  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopSeesConsolidatedReplyNotification() {
    Notification notification = createConsolidatedPostReplyNotification(User.USER_11);
    Notifications notificationsList = getNotificationsDesktop();

    Assertion.assertTrue(notificationsList.contains(notification));
  }

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP)
  @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopMarksAllNotificationsAsRead() {
    Notifications notificationsList = getNotificationsDesktop();
    notificationsList.markAllAsRead();
    Assertion.assertFalse(notificationsList.isAnyNotificationUnread());
  }

  /**
   * Test methods - MOBILE
   */

  @Execute(asUser =  User.USER_12, onWikia = WIKI_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = MOBILE)
  public void userOnMobileReceivesPostReplyNotification() {
    Notification notification = createPostReplyNotification(User.USER_12, User.USER_2);

    Assertion.assertTrue(getNotificationsMobile().contains(notification));
  }

  @Execute(asUser = User.USER_12, onWikia = WIKI_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = MOBILE)
  public void userOnMobileReceivesPostUpvoteNotification() {
    Notification notification = createPostUpvoteNotification(User.USER_12, User.USER_2);

    Assertion.assertTrue(getNotificationsMobile().contains(notification));
  }

  @Execute(asUser = User.USER_12, onWikia = WIKI_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = MOBILE)
  public void userOnMobileReceivesReplyUpvoteNotification() {
    Notification notification = createReplyUpvoteNotification(User.USER_12, User.USER_12, User.USER_2);

    Assertion.assertTrue(getNotificationsMobile().contains(notification));
  }

  @Execute(asUser = User.USER_12, onWikia = WIKI_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5)
  @Test(groups = MOBILE)
  public void userOnMobileSeesConsolidatedReplyNotification() {
    Notification notification = createConsolidatedPostReplyNotification(User.USER_12);
    Notifications notificationsList = getNotificationsMobile();

    Assertion.assertTrue(notificationsList.contains(notification));
  }

  /**
   * Helper methods
   */

  private PostEntity.Data createPostAs(User user) {
    return DiscussionsClient.using(user, driver).createPostWithUniqueData(siteId);
  }

  private ReplyEntityData createReplyToPostAs(PostEntity.Data post, User user) {
    return DiscussionsClient.using(user, driver).createReplyToPost(siteId, post);
  }

  private void upvotePostAs(PostEntity.Data post, User user) {
    DiscussionsClient.using(user, driver).upvotePost(siteId, post);
  }

  private void upvoteReplyAs(ReplyEntityData reply, User user) {
    DiscussionsClient.using(user, driver).upvoteReply(siteId, reply);
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
    ReplyEntityData reply = createReplyToPostAs(post, replyAuthor);
    upvoteReplyAs(reply, upvoteAuthor);
    return NotificationFactory.getReplyUpvoteNotification();
  }

  private Notification createConsolidatedPostReplyNotification(User postAuthor) {
    PostEntity.Data post = createPostAs(postAuthor);
    for(User replyAuthor : replyUsers) {
      createReplyToPostAs(post, replyAuthor);
    }
    return NotificationFactory.getPostReplyConsolidatedNotification(replyUsers.get(5), 5, post);
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
