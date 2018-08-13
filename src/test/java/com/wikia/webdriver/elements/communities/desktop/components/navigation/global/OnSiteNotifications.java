package com.wikia.webdriver.elements.communities.desktop.components.navigation.global;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a Page object for On Site Notifications with most of notifications
 * For Message Wall Notification there's MessagesNotifications
 */

public class OnSiteNotifications extends BasePage {

    @FindBy(css = "#onSiteNotificationsDropdown")
    private WebElement notificationsDropdownEntryPoint;

    @FindBy(css = "#notificationsContainer")
    private WebElement notificationsDropdown;

    @FindBys(@FindBy(css = ".wds-notifications__dropdown-content .wds-is-unread"))
    private List<WebElement> notificationsList;

    public OnSiteNotifications() {
        super();
    }

    /**
     * Hover mouse cursor over the notification bubble and wait for it to expand
     */
    public OnSiteNotifications openDropdown() {
        if(!isVisible(notificationsDropdown)) {
            Log.info("Notification dropdown is not visible;");
            wait.forElementVisible(notificationsDropdownEntryPoint).click();
            Log.info("Notification entry point was clicked");
        }

        return this;
    }

    /**
     * Expand the notifications and wait until the notifications for the current wiki are loaded
     */
    public OnSiteNotifications showMessagesNotifications() {
        openDropdown();
        Log.log("#WallNotifications li ul.subnav", "show notifications", true);

        return this;
    }


    /**
     * This should be called after expanding the notifications dropdown It will return a list of
     * unread notifications that have a given title
     */
    public List<WebElement> getUnreadNotificationsForTitle(String title) {
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
     * Returns true if there's visible a notification with specific title
     */
    public boolean isNotificationWithGivenTitleVisible(String messageTitle) {
        Boolean isNotificationVisible = false;
        if (getUnreadNotificationsForTitle(messageTitle).size() > 0) {
            isNotificationVisible = true;
        }

        return isNotificationVisible;
    }

}
