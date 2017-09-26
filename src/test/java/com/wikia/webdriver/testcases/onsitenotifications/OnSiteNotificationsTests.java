package com.wikia.webdriver.testcases.onsitenotifications;

import com.google.common.collect.Lists;
import com.wikia.webdriver.common.contentpatterns.MercurySubpages;
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
import com.wikia.webdriver.elements.mercury.pages.ArticlePage;
import com.wikia.webdriver.elements.mercury.pages.discussions.PostsListPage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notification;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.NotificationFactory;
import com.wikia.webdriver.pageobjectsfactory.pageobject.notifications.Notifications;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class OnSiteNotificationsTests extends NewTestTemplate {

  private static final String DESKTOP = "on-site-notifications-desktop";
  private static final String MOBILE = "on-site-notifications-mobile";

  private static final String ALL_READ = "All notifications should be marked as read on %s page";
  private static final String NOTIFICATION_VISIBLE_MSG = "Notification [%s] should be displayed on %s page";
  private static final String ARTICLE = "article";
  private static final String DISCUSSION = "discussion";

  private String siteId;
  private static final String WIKI_DESKTOP = MercuryWikis.DISCUSSIONS_3;
  private static final String WIKI_MOBILE = MercuryWikis.DISCUSSIONS_4;
  private List<User> replyUsers = Lists.newArrayList(
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

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP) @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopReceivesPostReplyNotification() {
    Notification notification = createReplyReturningExpectedNotification(User.USER_11, User.USER_2);

    verifyNotificationDisplayedOnDesktop(notification);
  }

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP) @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopReceivesPostUpvoteNotification() {
    Notification notification =
      createPostUpvoteReturningExpectedNotification(User.USER_11, User.USER_2);

    verifyNotificationDisplayedOnDesktop(notification);
  }

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP) @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopReceivesReplyUpvoteNotification() {
    Notification notification =
      createReplyUpvoteReturningExpectedNotification(User.USER_11, User.USER_11, User.USER_2);

    verifyNotificationDisplayedOnDesktop(notification);
  }

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP) @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopSeesConsolidatedReplyNotification() {
    Notification notification = createRepliesReturningConsolidatedNotification(User.USER_11);

    verifyNotificationDisplayedOnDesktop(notification);
  }

  @Execute(asUser = User.USER_11, onWikia = WIKI_DESKTOP) @Test(groups = DESKTOP)
  @InBrowser(emulator = Emulator.DESKTOP_BREAKPOINT_BIG)
  public void userOnDesktopMarksAllNotificationsAsRead() {
    Notifications notificationsList = getNotificationsOnDiscussionsPageDesktop();
    notificationsList.markAllAsRead();
    Assertion.assertFalse(notificationsList.isAnyNotificationUnread(),
      String.format(ALL_READ, DISCUSSION));
    Assertion.assertFalse(getNotificationsOnArticlePageDesktop().isAnyNotificationUnread(),
      String.format(ALL_READ, ARTICLE));
  }

  /**
   * Test methods - MOBILE
   */

  @Execute(asUser = User.USER_12, onWikia = WIKI_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5) @Test(groups = MOBILE)
  public void userOnMobileReceivesPostReplyNotification() {
    Notification notification = createReplyReturningExpectedNotification(User.USER_12, User.USER_2);

    verifyNotificationDisplayedOnMobile(notification);
  }

  @Execute(asUser = User.USER_12, onWikia = WIKI_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5) @Test(groups = MOBILE)
  public void userOnMobileReceivesPostUpvoteNotification() {
    Notification notification =
      createPostUpvoteReturningExpectedNotification(User.USER_12, User.USER_2);

    verifyNotificationDisplayedOnMobile(notification);
  }

  @Execute(asUser = User.USER_12, onWikia = WIKI_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5) @Test(groups = MOBILE)
  public void userOnMobileReceivesReplyUpvoteNotification() {
    Notification notification =
      createReplyUpvoteReturningExpectedNotification(User.USER_12, User.USER_12, User.USER_2);

    verifyNotificationDisplayedOnMobile(notification);
  }

  @Execute(asUser = User.USER_12, onWikia = WIKI_MOBILE)
  @InBrowser(browser = Browser.CHROME, emulator = Emulator.GOOGLE_NEXUS_5) @Test(groups = MOBILE)
  public void userOnMobileSeesConsolidatedReplyNotification() {
    Notification notification = createRepliesReturningConsolidatedNotification(User.USER_12);

    verifyNotificationDisplayedOnMobile(notification);
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

  private Notification createReplyReturningExpectedNotification(User postAuthor, User replyAuthor) {
    PostEntity.Data post = createPostAs(postAuthor);
    createReplyToPostAs(post, replyAuthor);
    return NotificationFactory.getPostReplyNotification(replyAuthor, post);
  }

  private Notification createPostUpvoteReturningExpectedNotification(User postAuthor, User upvoteAuthor) {
    PostEntity.Data post = createPostAs(postAuthor);
    upvotePostAs(post, upvoteAuthor);
    return NotificationFactory.getPostUpvoteNotification(post);
  }

  private Notification createReplyUpvoteReturningExpectedNotification(User postAuthor, User replyAuthor, User upvoteAuthor) {
    PostEntity.Data post = createPostAs(postAuthor);
    ReplyEntityData reply = createReplyToPostAs(post, replyAuthor);
    upvoteReplyAs(reply, upvoteAuthor);
    return NotificationFactory.getReplyUpvoteNotification();
  }

  private Notification createRepliesReturningConsolidatedNotification(User postAuthor) {
    PostEntity.Data post = createPostAs(postAuthor);
    replyUsers.forEach(replyAuthor -> createReplyToPostAs(post, replyAuthor));
    return NotificationFactory.getPostReplyConsolidatedNotification(replyUsers.get(5), 5, post);
  }

  private Notifications getNotificationsOnDiscussionsPageDesktop() {
    return getNotificationsDesktop(new PostsListPage().open());

  }

  private Notifications getNotificationsOnArticlePageDesktop() {
    return getNotificationsDesktop(
      new ArticlePageObject().openArticleByPath(MercurySubpages.MAIN_PAGE));
  }

  private Notifications getNotificationsDesktop(WikiBasePageObject page) {
    return page
      .getNotificationsDropdown()
      .expand()
      .getNotifications();
  }

  private Notifications getNotificationsOnDiscussionsPageMobile() {
    return getNotificationsMobile(new PostsListPage().open());
  }

  private Notifications getNotificationsOnArticlePageMobile() {
    return getNotificationsMobile(new ArticlePage().open(MercurySubpages.MAIN_PAGE));
  }

  private Notifications getNotificationsMobile(WikiBasePageObject page) {
    return page
      .getTopBar()
      .openNavigation()
      .openUserProfile()
      .getNotifications();
  }

  private String getMessageFor(Notification notification, String page) {
    return String.format(NOTIFICATION_VISIBLE_MSG, notification.getContent(), page);
  }

  private void verifyNotificationDisplayedOnDesktop(Notification notification) {
    Assertion.assertTrue(getNotificationsOnDiscussionsPageDesktop().contains(notification),
      getMessageFor(notification, DISCUSSION));
    Assertion.assertTrue(getNotificationsOnArticlePageDesktop().contains(notification),
      getMessageFor(notification, ARTICLE));
  }

  private void verifyNotificationDisplayedOnMobile(Notification notification) {
    Assertion.assertTrue(getNotificationsOnDiscussionsPageMobile().contains(notification),
      getMessageFor(notification, DISCUSSION));
    Assertion.assertTrue(getNotificationsOnArticlePageMobile().contains(notification),
      getMessageFor(notification, ARTICLE));
  }
}
