/**
 *
 */
package com.wikia.webdriver.testcases.notificationstests.messagewallnotifications;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.annotations.RelatedIssue;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NotificationsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;
import com.wikia.webdriver.pageobjectsfactory.pageobject.special.watch.WatchPageObject;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak <p/> 1. User 5 is following user 6 message wall 2. User 6 is
 *         writig a message on his own message wall, 3. User 5 is notified about the message
 */
public class MessageWallNotificationsFollowersMessageTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  String title;

  @Test(
      groups = {
          "MessageWallNotificationsFollowersMessageTests_001",
          "MessageWallNotificationsFollowersMessageTests"
      }
  )
  public void followerNotificationNewMessage_setup_1() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName5, credentials.password5, wikiURL);
    MessageWall wall = new MessageWall(driver).open(credentials.userName6);
    WatchPageObject watch = wall.unfollowCurrentUrl();
    watch.confirmWatchUnwatch();
    wall.follow();
  }

  @Test(
      groups = {
          "MessageWallNotificationsFollowersMessageTests_002",
          "MessageWallNotificationsFollowersMessageTests"
      },
      dependsOnMethods = "followerNotificationNewMessage_setup_1"
  )
  public void followerNotificationNewMessage_setup_2() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName6, credentials.password6, wikiURL);
    MessageWall wall = new MessageWall(driver).open(credentials.userName6);
    for (int i = 0; i<51; i++) {
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName6);
  }}

  @Test(
      groups = {
          "MessageWallNotificationsFollowersMessageTests_003",
          "MessageWallNotificationsFollowersMessageTests"
      },
      dependsOnMethods = "followerNotificationNewMessage_setup_2"
  )
  @RelatedIssue(issueID = "MAIN-5924",
                comment = "the issue is valid only if the veryfing user "
                        + "has more than 50 notifications the veryfing user")
  public void followerNotificationNewMessage_verification() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName5, credentials.password5, wikiURL);
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
    notifications.showNotifications();
    notifications.verifyNotification(title, credentials.userName6);
  }


}
