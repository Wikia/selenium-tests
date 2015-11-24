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

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak 1. User 5 is posting message on user 6 message wall, 2. User 6
 *         is replying to user 5 message, 3. USer 5 is notified about reply
 */
public class MessageWallNotificationsThreadCreatorTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  String title;

  @Test(
      groups = {
          "MessageWallNotificationsThreadCreatorTests_001",
          "MessageWallNotificationsThreadCreatorTests"
      }
  )
  public void threadCreatorNotification_setup_1() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName11, credentials.password11, wikiURL);
    MessageWall wall = new MessageWall(driver).open(credentials.userName12);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName11);
  }

  @Test(
      groups = {
          "MessageWallNotificationsThreadCreatorTests_002",
          "MessageWallNotificationsThreadCreatorTests"
      },
      dependsOnMethods = "threadCreatorNotification_setup_1"
  )
  public void threadCreatorNotification_setup_2() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName12, credentials.password12, wikiURL);
    MessageWall wall = new MessageWall(driver).open(credentials.userName12);
    MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndQuoteMessageWall(reply);
    ;
    wall.submitQuote();
    wall.verifyQuote(reply);
  }

  @Test(
      groups = {
          "MessageWallNotificationsThreadCreatorTests_003",
          "MessageWallNotificationsThreadCreatorTests"
      },
      dependsOnMethods = "threadCreatorNotification_setup_2"
  )
  @RelatedIssue(issueID = "MAIN-5924",
                comment = "the issue is valid only if the veryfing user "
                        + "has more than 50 notifications the veryfing user")
  public void threadCreatorNotification_verification() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.loginAs(credentials.userName11, credentials.password11, wikiURL);
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
    notifications.showNotifications();
    notifications.verifyNotification(title, credentials.userName12, "replied to your message");
  }
}
