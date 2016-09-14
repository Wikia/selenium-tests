package com.wikia.webdriver.testcases.notificationstests.messagewallnotifications;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.global_navitagtion.NotificationsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.MessageWall;

import org.testng.annotations.Test;
@Test(groups = {"NotificationsTests", "MessageWallNotificationsThreadCreatorTests"})
public class MessageWallNotificationsThreadCreatorTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  String title;

  @Test(
      groups = {"MessageWallNotificationsThreadCreatorTests_001"}
  )
  public void threadCreatorNotification_setup_1() {
    WikiBasePageObject base = new WikiBasePageObject();
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
      groups = {"MessageWallNotificationsThreadCreatorTests_002"},
      dependsOnMethods = "threadCreatorNotification_setup_1"
  )
  public void threadCreatorNotification_setup_2() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName12, credentials.password12, wikiURL);
    MessageWall wall = new MessageWall(driver).open(credentials.userName12);
    MiniEditorComponentObject miniReply = wall.triggerReplyMessageArea();
    String reply = PageContent.MESSAGE_WALL_QUOTE_PREFIX + wall.getTimeStamp();
    miniReply.switchAndQuoteMessageWall(reply);
    wall.submitQuote();
    wall.verifyQuote(reply);
  }

  @Test(
      groups = {"MessageWallNotificationsThreadCreatorTests_003"},
      dependsOnMethods = "threadCreatorNotification_setup_2"
  )
 public void userIsNotifiedWhenOtherUserWritesResponseOnHerMessageWal() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName11, credentials.password11, wikiURL);
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
    notifications.showNotifications();
    notifications.verifyNotification(title, credentials.userName12, "replied to your message");
  }
}
