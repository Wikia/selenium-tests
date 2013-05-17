package com.wikia.webdriver.TestCases.NotificationsTests;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.Core.CommonFunctions;
import com.wikia.webdriver.Common.Properties.Properties;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.ComponentObject.Notifications.NotificationsComponentObject;

public class MessageWallNotificationsTests extends TestTemplate {

	@Test(groups= {"MessageWallNotificationsTests_001", "MessageWallNotificationsTests",
			"NotificationsTests", "MessageWall"} )
	public void messageWallNotificationsTests_001_wallOwnerReceivesANotification() {
		CommonFunctions.logOut(driver);
		CommonFunctions.logIn(Properties.userName, Properties.password, driver);

		NotificationsComponentObject notifications = new NotificationsComponentObject(driver);
		notifications.showNotifications();
		notifications.clickNotifications();
		System.out.println("MECH " + notifications.getNumberOfUnreadNotifications());
	}
}
