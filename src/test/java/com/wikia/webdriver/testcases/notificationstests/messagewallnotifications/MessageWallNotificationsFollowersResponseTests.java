package com.wikia.webdriver.testcases.notificationstests.messagewallnotifications;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NotificationsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWallThreadPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.testng.annotations.Test;
@Test(groups = {"NotificationsTests", "MessageWallNotificationsFollowersResponseTests"})
public class MessageWallNotificationsFollowersResponseTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  String title;

  @Test(
      groups = {"MessageWallNotificationsFollowersResponseTests_001"}
  )
  public void followerNotificationResponse_setup_1() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName7, credentials.password7, wikiURL);
    MessageWall wall = new MessageWall().open(credentials.userName8);
    WatchPageObject watch = wall.unfollowCurrentUrl();
    watch.confirmWatchUnwatch();
  }

  @Test(
      groups = {"MessageWallNotificationsFollowersResponseTests_002" },
      dependsOnMethods = "followerNotificationResponse_setup_1"
  )
  public void followerNotificationResponse_setup_2() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName8, credentials.password8, wikiURL);
    MessageWall wall = new MessageWall().open(credentials.userName8);
    MiniEditorComponentObject mini = wall.triggerMessageArea(true);
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName8);
  }

  @Test(
      groups = {"MessageWallNotificationsFollowersResponseTests_003"},
      dependsOnMethods = "followerNotificationResponse_setup_2"
  )
  public void followerNotificationResponse_setup_3() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName7, credentials.password7, wikiURL);
    MessageWall wall = new MessageWall().open(credentials.userName8);
    wall.follow();
  }

  @Test(
      groups = {"MessageWallNotificationsFollowersResponseTests_004"},
      dependsOnMethods = "followerNotificationResponse_setup_3"
  )
  public void followerNotificationResponse_setup_4() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName8, credentials.password8, wikiURL);
    MessageWall wall = new MessageWall().open(credentials.userName8);
    MessageWallThreadPageObject thread = wall.openThread(title);
    MiniEditorComponentObject miniReply = thread.triggerMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndWrite(reply);
    thread.submitQuote();
    thread.verifyLastReply(credentials.userName8, reply);
  }


  @Test(groups = {"MessageWallNotificationsFollowersResponseTests_005"},
        dependsOnMethods = "followerNotificationResponse_setup_4")
 public void userIsNotifiedWhenOtherUserWritesResponseOnFollowedMessageWall() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName7, credentials.password7, wikiURL);
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
    notifications.showNotifications();
    notifications.verifyNotification(title, credentials.userName8);
  }
}
