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
@Test(groups = {"NotificationsTests", "MessageWallNotificationsOwnerTests"})
public class MessageWallNotificationsOwnerTests extends NewTestTemplate {

  Credentials credentials = Configuration.getCredentials();

  String title;

  @Test(
      groups = { "MessageWallNotificationsOwnerTests_001"}
  )
  public void wallOwnerReceivesNotification_setup() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName9, credentials.password9, wikiURL);
    MessageWall wall = new MessageWall(driver).open(credentials.userName10);
    MiniEditorComponentObject mini = wall.triggerMessageArea();
    String message = PageContent.MESSAGE_WALL_MESSAGE_PREFIX + wall.getTimeStamp();
    title = PageContent.MESSAGE_WALL_TITLE_PREFIX + wall.getTimeStamp();
    mini.switchAndWrite(message);
    wall.writeTitle(title);
    wall.submit();
    wall.verifyMessageText(title, message, credentials.userName9);
  }

  @Test(
      groups = {"MessageWallNotificationsOwnerTests_002"},
      dependsOnMethods = "wallOwnerReceivesNotification_setup"
  )
 public void userIsNotifiedWhenOtherUserWritesMessageOnHerMessageWal() {
    WikiBasePageObject base = new WikiBasePageObject();
    base.loginAs(credentials.userName10, credentials.password10, wikiURL);
    NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
    notifications.showNotifications();
    notifications.verifyNotification(title, credentials.userName9);
  }
}
