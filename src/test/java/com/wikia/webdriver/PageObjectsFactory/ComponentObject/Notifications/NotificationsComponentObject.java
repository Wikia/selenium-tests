package com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;

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

	private By toolsList = By.cssSelector("ul.tools li");
	
	/**
	 * shown notifications 
	 */
	public void showNotifications() {
		builder.moveToElement(notifications).build().perform();
		waitForElementVisibleByElement(notificationsSubnav);
		PageObjectLogging.log("#WallNotifications li ul.subnav", "show notifications", true, driver);
	}
	
	/**
	 * click notifications bubble
	 */
	public void clickNotifications() {
		waitForElementByElement(notificationsBubbles);
		clickAndWait(notificationsBubbles);
		PageObjectLogging.log("clickshowNotifications", "click on notifications bubbles", true, driver);
	}

	public String getNotificationLink(int notificationNumber) {
		waitForElementByElement(notificationsList.get(notificationNumber - 1));
		PageObjectLogging.log("unrollNotifications", "click on notifications bubbles", true, driver);
		return notificationsList.get(notificationNumber - 1).getAttribute("href");
	}
	
	public int getNumberOfUnderNotifications() {
		String text = bubblesCount.getText();
		if (!text.isEmpty()) {
			return Integer.parseInt(text);
		}
		return 0;
	}
	
}