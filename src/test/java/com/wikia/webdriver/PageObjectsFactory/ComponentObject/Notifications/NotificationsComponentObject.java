package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.wikia.webdriver.Common.Core.CommonExpectedConditions;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.lang.Thread;

public class NotificationsComponentObject extends BasePageObject{
	
	public NotificationsComponentObject(WebDriver driver) {
		super(driver);
	}
	@FindBy(css="div.bubbles")
	protected WebElement notificationsBubbles;
	@FindBys(@FindBy(css="li.unread_notification a"))
	private List<WebElement> notificationsList;
	@FindBy(css="#WallNotifications li ul.subnav")
	private WebElement notificationsSubnav;
	@FindBy(css="#WallNotifications")
	private WebElement notifications;
	@FindBy(css="#bubbles_count")
	private WebElement bubblesCount;	

	/**
	 * expand the notifications and wait until the notifications for the current wiki are loaded
	 */
	public void showNotifications() {
		this.waitForNotificationsLoaded();
		executeScript("$('#WallNotifications li ul.subnav').addClass('show');$('#WallNotifications').mouseover();"); 
		builder.moveToElement(notifications).build().perform();
		waitForElementVisibleByElement(notificationsSubnav);
		PageObjectLogging.log("#WallNotifications li ul.subnav", "show notifications", true, driver);
		waitForElementPresenceByBy(By.cssSelector("#WallNotifications .subnav li.notifications-for-wiki:nth-child(2)"));		
		waitForElementNotPresent("#WallNotifications .subnav li.notifications-for-wiki:nth-child(2) li.notifications-empty");
	}
	
	/**
	 * click notifications bubble
	 * @todo: is this needed? the notifications expand on mouse hover
	 * so we should use the showNotifications method
	 */
	public void clickNotifications() {
		waitForElementByElement(notificationsBubbles);
		clickAndWait(notificationsBubbles);
		PageObjectLogging.log("clickshowNotifications", "click on notifications bubbles", true, driver);
	}

	/**
	 * Wait until ajax call updates the notifications status (showing the number of unread notifications)
	 */
	public void waitForNotificationsLoaded() {
		waitForElementNotPresent("#WallNotifications .subnav > li.notifications-empty");
	}
	
	public String getNotificationLink(int notificationNumber) {
		waitForElementByElement(notificationsList.get(notificationNumber - 1));
		PageObjectLogging.log("unrollNotifications", "click on notifications bubbles", true, driver);
		return notificationsList.get(notificationNumber - 1).getAttribute("href");
	}
	
	/**
	 * Return the number of unread notifications
	 */
	public int getNumberOfUnreadNotifications() {
		this.waitForNotificationsLoaded();
		String text = bubblesCount.getText();
		if (!text.isEmpty()) {
			return Integer.parseInt(text);
		}
		return 0;
	}
	
	/**
	 * This should be called after expanding the notifications dropdown
	 * Ii will return a list of unread notifications that have a given title
	 */
	public ArrayList<WebElement> getUnreadNotificationsForTitle(String title) {
		ArrayList<WebElement> notifications = new ArrayList<WebElement>();
		for (int i = 0; i < this.notificationsList.size(); i++) {
			WebElement n = this.notificationsList.get(i);
			WebElement nTitle = n.findElement(By.cssSelector("div.msg-title"));
			if (n != null) {
				if (title.equals(nTitle.getText())) {
					notifications.add(n);
				}
			}
		}		
		return notifications;
	}
	
}