/**
 *
 */
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

import org.testng.annotations.Test;

/**
 * 1. User 5 is writing message on his message wall, 2. User 6 is replying on user 5 message wall,
 * 3. User 5 is replying on user 6 message wall, 3. User 6 is notified about reply on user's 5
 * message wall
 */
@Test(groups = {"NotificationsTests", "MessageWallNotificationsThreadParticipantTests"})
public class MessageWallNotificationsThreadParticipantTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  String title;

  @Test(
      groups = {"MessageWallNotificationsThreadParticipantTests_001"}
  )
  public void threadCreatorNotification_setup_1() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName3, credentials.password3, wikiURL);
    MessageWall wall = new MessageWall().open(credentials.userName3);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.setTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName3);
  }

  @Test(
      groups = {"MessageWallNotificationsThreadParticipantTests_002"},
      dependsOnMethods = "threadCreatorNotification_setup_1"
  )
  public void threadCreatorNotification_setup_2() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName4, credentials.password4, wikiURL);
    MessageWall wall = new MessageWall().open(credentials.userName3);
    MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndQuoteMessageWall(reply);
    wall.submitQuote();
    wall.verifyQuote(reply);
  }

  @Test(
      groups = {"MessageWallNotificationsThreadParticipantTests_003"},
      dependsOnMethods = "threadCreatorNotification_setup_2"
  )
  public void threadCreatorNotification_setup_3() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName3, credentials.password3, wikiURL);
    MessageWall wall = new MessageWall().open(credentials.userName3);
    MessageWallThreadPageObject thread = wall.openThread(title);
    MiniEditorComponentObject miniReply = thread.triggerMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndWrite(reply);
    thread.submitQuote();
    thread.verifyLastReply(credentials.userName3, reply);
  }

  @Test(
      groups = {"MessageWallNotificationsThreadParticipantTests_004"},
      dependsOnMethods = "threadCreatorNotification_setup_3"
  )
  public void userIsNotifiedWhenOtherUserWritesResponseToHerResponseOnMessageWal() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName4, credentials.password4, wikiURL);
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
    notifications.showNotifications();
    notifications
        .verifyNotification(title, credentials.userName3, "replied to " + credentials.userName3);
  }
}
