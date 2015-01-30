/**
 *
 */
package com.wikia.webdriver.testcases.notificationstests.MessageWallNotifications;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.pageobjectsfactory.componentobject.minieditor.MiniEditorComponentObject;
import com.wikia.webdriver.pageobjectsfactory.componentobject.notifications.NotificationsComponentObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.messagewall.NewMessageWall;

import org.testng.annotations.Test;

/**
 * @author Karol 'kkarolk' Kujawiak <p/> 1. User 5 is posting message on user 6 message wall 2. User
 *         6 is notified about user 6 message
 */
public class MessageWallNotificationsOwnerTests extends NewTestTemplate {

  Credentials credentials = config.getCredentials();

  String title;

  @Test(
      groups = {
          "MessageWallNotificationsOwnerTests_001",
          "MessageWallNotificationsOwnerTests"
      }
  )
  public void wallOwnerReceivesNotification_setup() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName9, credentials.password9, wikiURL);
    NewMessageWall wall = base.openMessageWall(credentials.userName10, wikiURL);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName9);
  }

  @Test(
      groups = {
          "MessageWallNotificationsOwnerTests_002",
          "MessageWallNotificationsOwnerTests"
      },
      dependsOnMethods = "wallOwnerReceivesNotification_setup"
  )
  public void wallOwnerReceivesNotification_verification() {
    WikiBasePageObject base = new WikiBasePageObject(driver);
    base.logInCookie(credentials.userName10, credentials.password10, wikiURL);
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
    notifications.showNotifications();
    notifications.verifyNotification(title, credentials.userName9);
  }

}
