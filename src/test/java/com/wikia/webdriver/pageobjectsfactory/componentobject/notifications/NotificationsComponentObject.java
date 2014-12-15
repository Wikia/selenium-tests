package com.wikia.webdriver.pageobjectsfactory.componentobject.notifications;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationsComponentObject extends BasePageObject {

	public NotificationsComponentObject(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div.bubbles")
	protected WebElement notificationsBubbles;
	@FindBys(@FindBy(css = ".notification.unread"))
	private List<WebElement> notificationsList;
	@FindBy(css = "#AccountNavigation .subnav")
	private WebElement notificationsSubnav;
	@FindBy(css = ".bubbles-count")
	private WebElement bubblesCount;
	@FindBy(css = "#wall-notifications-markasread-sub")
	private WebElement markNotificationsAsRead;
	@FindBy(css = "#wall-notifications-markasread-all-wikis")
	private WebElement markNotificationsAsReadAllWikis;
	@FindBy(css = "#wall-notifications-markasread-this-wiki")
	private WebElement markNotificationsAsReadThisWiki;
	@FindBy(css = "#AccountNavigation .bubbles")
	private WebElement emptyNumberOfUnreadNotifications;
	@FindBy(css = "#AccountNavigation")
	private WebElement accountNavigationEntryPoint;
	@FindBy(css = "#notifications .notification-message")
	private WebElement notificationsMessage;
	private By notificationDropdownForCurrentWiki = By
		.cssSelector("#WallNotifications .subnav li.notifications-for-wiki:nth-child(2)");
	private By emptyNotificationDropdownForCurrentWiki = By
		.cssSelector("#WallNotifications .subnav li.notifications-for-wiki:nth-child(2) li.notifications-empty");
	private By notificationTitle = By.cssSelector("div.msg-title");
	private By unreadNotificationReddot = By
		.cssSelector("#WallNotifications > li > div.reddot");

	/**
	 * hover the mouse over the notification bubble and wait for it to expand
	 */
	private void openNotifications() {
		new WebDriverWait(driver, 20, 2000).until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver webDriver) {
				if (!notificationsSubnav.isDisplayed()) {
					accountNavigationEntryPoint.click();

					return false;
				}
				return true;
			}
		});
	}

	/**
	 * After expanding the notifications dropdown, the notification messages are
	 * loaded using ajax request. This method waits until this requests
	 * completes.
	 */
	private void waitForNotificationsMessagesToLoad() {
		waitForElementVisibleByElement(notificationsSubnav);
		waitForElementPresenceByBy(notificationDropdownForCurrentWiki);
		waitForElementNotPresent(emptyNotificationDropdownForCurrentWiki);
	}

	/**
	 * expand the notifications and wait until the notifications for the current
	 * wiki are loaded
	 */
	public void showNotifications() {
		waitForNotificationsLoaded();
		openNotifications();
		PageObjectLogging.log("#WallNotifications li ul.subnav",
			"show notifications", true);
	}

	/**
	 * click notifications bubble
	 *
	 * @todo: is this needed? the notifications expand on mouse hover so we
	 * should use the showNotifications method
	 */
	public void clickNotifications() {
		waitForElementByElement(notificationsBubbles);
		scrollAndClick(notificationsBubbles);
		PageObjectLogging.log("clickshowNotifications",
			"click on notifications bubbles", true);
	}

	/**
	 * Wait until ajax call updates the notifications status (showing the number
	 * of unread notifications)
	 */
	private void waitForNotificationsLoaded() {
		waitForElementVisibleByElement(emptyNumberOfUnreadNotifications);
	}

	/**
	 * Fetches the address that of notification with specified text points to.
	 */
	public String getNotificationLink(String text) {
		for (int i = 0; i < notificationsList.size(); i++) {
			if (notificationsList.get(i)
				.findElement(By.cssSelector("div.notification-message")).getText()
				.contains(text)) {
				PageObjectLogging.log("getNotificationLink",
					"get addres that of " + i + 1
						+ " notification points to", true);
				return notificationsList.get(i).getAttribute("href");
			}
		}
		PageObjectLogging.log("getNotificationLink",
			"No notification that contains the following text: " + text,
			false);
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
	 * This should be called after expanding the notifications dropdown It will
	 * return a list of unread notifications that have a given title
	 */
	private ArrayList<WebElement> getUnreadNotificationsForTitle(String title) {
		ArrayList<WebElement> notifications = new ArrayList<WebElement>();
		for (int i = 0; i < this.notificationsList.size(); i++) {
			WebElement n = this.notificationsList.get(i);
			WebElement nTitle = n.findElement(By.cssSelector(".notification-message h4"));
			if (n != null && title.equals(nTitle.getText())) {
				notifications.add(n);
			}
		}
		return notifications;
	}

	/**
	 * This should be called after expanding the notifications dropdown. It
	 * marks all the notifications as read
	 */
	public void clickMarkNotificationsAsRead() {
		if (this.getNumberOfUnreadNotifications() > 0) {
			if (this.markNotificationsAsReadThisWiki.isDisplayed()) {
				this.scrollAndClick(this.markNotificationsAsReadThisWiki);
			} else {
				this.scrollAndClick(this.markNotificationsAsRead);
				this.waitForElementVisibleByElement(this.markNotificationsAsReadAllWikis);
				this.scrollAndClick(this.markNotificationsAsReadAllWikis);
			}
			this.waitForElementNotPresent(unreadNotificationReddot);
		}
	}

	/**
	 * This should be called after showNotifications method
	 *
	 * @param messageTitle
	 * @param messageAuthor
	 */
	public void verifyNotification(String messageTitle, String messageAuthor) {
		Assertion.assertNotEquals(0, getNumberOfUnreadNotifications());
		ArrayList<WebElement> notificationsListForTitle = getUnreadNotificationsForTitle(messageTitle);
		Assertion.assertEquals(1, notificationsListForTitle.size());
		String notificationMessageBody = notificationsListForTitle.get(0)
			.findElement(By.cssSelector("p")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(messageAuthor));
	}

	/**
	 * This should be called after showNotifications method
	 *
	 * @param messageTitle
	 * @param messageAuthor
	 */
	public void verifyNotification(String messageTitle, String messageAuthor, String messageContent) {
		Assertion.assertNotEquals(0, getNumberOfUnreadNotifications());
		ArrayList<WebElement> notificationsListForTitle = getUnreadNotificationsForTitle(messageTitle);
		Assertion.assertEquals(1, notificationsListForTitle.size());
		String notificationMessageBody = notificationsListForTitle.get(0)
			.findElement(By.cssSelector("div.notification-message")).getText();
		Assertion.assertTrue(notificationMessageBody.contains(messageAuthor));
		Assertion.assertTrue(notificationMessageBody.contains(messageContent));
	}

	public void markAllNotificationsAsRead() {
		if (getNumberOfUnreadNotifications() > 0) {
			showNotifications();
			clickMarkNotificationsAsRead();
		}
	}
}
