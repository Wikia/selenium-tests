package com.wikia.webdriver.elements.communities.desktop.components.navigation.global;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Page object for Messages Notifications related with Message Wall.
 * NOT general on site notifications.
 */

public class MessagesNotifications extends WikiBasePageObject {

  @FindBy(css = "#notificationsEntryPoint div.bubbles")
  protected WebElement notificationsBubbles;
  @FindBys(@FindBy(css = "#notificationsEntryPoint .notification.unread"))
  private List<WebElement> notificationsList;
  @FindBy(css = "#notificationsContainer")
  private WebElement messagesNotificationsDropdown;
  @FindBy(css = ".bubbles .wds-global-navigation__notifications-counter")
  private WebElement bubblesCount;
  @FindBy(css = "#wall-notifications-markasread-sub")
  private WebElement markNotificationsAsRead;
  @FindBy(css = "#wall-notifications-markasread-all-wikis")
  private WebElement markNotificationsAsReadAllWikis;
  @FindBy(css = "#wall-notifications-markasread-this-wiki")
  private WebElement markNotificationsAsReadThisWiki;
  @FindBy(css = "#notificationsEntryPoint .bubbles")
  private WebElement emptyNumberOfUnreadNotifications;
  @FindBy(css = "#notificationsEntryPoint")
  private WebElement accountNavigationEntryPoint;

  private By unreadNotificationReddot = By.cssSelector("#WallNotifications > li > div.reddot");

  public MessagesNotifications() {
    super();
  }

  /**
   * hover the mouse over the notification bubble and wait for it to expand
   */
  private void openDropdown() {
    new WebDriverWait(driver, 20, 2000).until(new ExpectedCondition<Boolean>() {
      @Override
      public Boolean apply(WebDriver webDriver) {
        if (!messagesNotificationsDropdown.isDisplayed()) {
          accountNavigationEntryPoint.click();

          return false;
        }
        return true;
      }
    });
  }

  /**
   * expand the notifications and wait until the notifications for the current wiki are loaded
   */
  public void showMessagesNotifications() {
    waitForNotificationsLoaded();
    openDropdown();
    Log.log("#WallNotifications li ul.subnav", "show notifications", true);
  }

  /**
   * Wait until ajax call updates the notifications status (showing the number of unread
   * notifications)
   */
  private void waitForNotificationsLoaded() {
    wait.forElementVisible(emptyNumberOfUnreadNotifications);
  }

  /**
   * Fetches the address that of notification with specified text points to.
   */
  public String getNotificationLink(String text) {
    for (int i = 0; i < notificationsList.size(); i++) {
      if (notificationsList.get(i)
          .findElement(By.cssSelector(".notification-message"))
          .getText()
          .contains(text)) {
        Log.log("getNotificationLink",
                "get addres that of " + i + 1 + " notification points to",
                true
        );
        return notificationsList.get(i).findElement(By.tagName("a")).getAttribute("href");
      }
    }
    Log.log("getNotificationLink",
            "No notification that contains the following text: " + text,
            false
    );
    return null;
  }

  /**
   * Return the number of unread notifications
   */
  private int getNumberOfUnreadNotifications() {
    this.waitForNotificationsLoaded();
    String text = bubblesCount.getText();
    if (!text.isEmpty()) {
      return Integer.parseInt(text);
    }
    return 0;
  }

  /**
   *
   * Get message body of a given notification with specific message title
   */

  public String getNotificationMessageBody(String messageTitle) {
    return getUnreadNotificationsForTitle(messageTitle)
            .get(0)
            .findElement(By.cssSelector("div.notification-message"))
            .getText();
  }

  /**
   * This should be called after expanding the notifications dropdown It will return a list of
   * unread notifications that have a given title
   */
  private List<WebElement> getUnreadNotificationsForTitle(String title) {
    List<WebElement> notifications = new ArrayList<WebElement>();
    for (int i = 0; i < this.notificationsList.size(); i++) {
      WebElement n = this.notificationsList.get(i);
      WebElement nTitle = n.findElement(By.cssSelector(".notification-message h4"));
      String text = nTitle.getText();
      if (n != null && text.equals(title)) {
        notifications.add(n);
      }
    }
    return notifications;
  }

  /**
   * This should be called after expanding the notifications dropdown. It marks all the
   * notifications as read
   */
  public void clickMarkNotificationsAsRead() {
    if (this.getNumberOfUnreadNotifications() > 0) {
      if (this.markNotificationsAsReadThisWiki.isDisplayed()) {
        this.scrollAndClick(this.markNotificationsAsReadThisWiki);
      } else {
        this.scrollAndClick(this.markNotificationsAsRead);
        this.wait.forElementVisible(this.markNotificationsAsReadAllWikis);
        this.scrollAndClick(this.markNotificationsAsReadAllWikis);
      }
      this.wait.forElementNotPresent(unreadNotificationReddot);
    }
  }

  /**
   * This should be called after showMessagesNotifications method
   */
  public void verifyNotification(String messageTitle, String messageAuthor) {
    Assertion.assertNotEquals(0, getNumberOfUnreadNotifications());
    List<WebElement> notificationsListForTitle = getUnreadNotificationsForTitle(messageTitle);
    Assertion.assertEquals(1, notificationsListForTitle.size());
    String notificationMessageBody = notificationsListForTitle.get(0)
        .findElement(By.cssSelector("p"))
        .getText();
    Assertion.assertStringContains(notificationMessageBody, messageAuthor);
  }

  /**
   * This should be called after showMessagesNotifications method
   */
  public void verifyNotification(String messageTitle, String messageAuthor, String messageContent) {
    Assertion.assertNotEquals(0, getNumberOfUnreadNotifications());
    List<WebElement> notificationsListForTitle = getUnreadNotificationsForTitle(messageTitle);
    Assertion.assertEquals(1, notificationsListForTitle.size());
    String notificationMessageBody = getNotificationMessageBody(messageTitle);
    Assertion.assertTrue(notificationMessageBody.contains(messageAuthor));
    Assertion.assertTrue(notificationMessageBody.contains(messageContent));
  }
}
