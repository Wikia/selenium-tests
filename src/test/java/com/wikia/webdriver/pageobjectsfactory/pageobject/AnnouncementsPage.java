package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.mobile.pages.discussions.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AnnouncementsPage extends BasePage {

    /**
     * Announcements is a feature page for admins & mods to create notifications for community's active users.
     * Active user is a person who contributed on wiki or discussion in the last 90 days.
     * Announcements are available via <wiki>/announcements URL, entry point is in the Admin Dashboard.
     * More info: https://community.wikia.com/wiki/Help:Notifications
     */

    private static final String URL_PATH = "/announcements";

    @FindBy(xpath = "(//textarea)[1]")
    private WebElement announcementTextField;

    @FindBy(xpath = "(//textarea)[2]")
    private WebElement urlField;

    @FindBy(css = ".wds-button[type='submit']")
    private WebElement postButton;

    @FindBy(css = ".announcements-list > div > h3 > .wds-button")
    private WebElement expireButton;

    @FindBy(css = ".wds-dialog__wrapper")
    private WebElement confirmationModal;

    @FindBy(xpath = "//div[@class='wds-dialog__actions']/button[2]")
    private WebElement confirmationModalOKButton;

    @FindBy(css = ".announcements-error")
    private WebElement errorMessage;

    @FindBy(css = ".page-header")
    private WebElement pageHeader;

    @FindBy(css = ".about-announcements")
    private WebElement aboutAnnouncements;

    @FindBy(css = ".announcements-form")
    private WebElement announcementForm;

    @FindBy(css = ".announcements-list")
    private WebElement pastAnnouncementsList;

    public AnnouncementsPage open() {
        driver.get(getUrlWithCacheBuster(String.format("%s%s", urlBuilder.getUrl(), URL_PATH)));
        return this;
    }

    public AnnouncementsPage addAnnouncementText(String announcementText) {
        wait.forElementClickable(announcementTextField);
        announcementTextField.sendKeys(announcementText);
        Log.info("Announcement's content was typed into input field");

        return this;
    }

    public AnnouncementsPage addUrl(String url){
        wait.forElementClickable(urlField);
        urlField.sendKeys(url);
        Log.info("URL was typed into input field");

        return this;
    }

    public AnnouncementsPage clickOKButton() {
        wait.forElementClickable(confirmationModalOKButton).click();
        Log.info("OK button on the confirmation modal was clicked");

        return this;
    }

    public AnnouncementsPage postNewAnnouncement(String announcement, String url) {
        if(isVisible(expireButton)) {
            //if there is already active announcement - expire it
            this.expireAnnouncement();
        }
        this.addAnnouncementText(announcement);
        this.addUrl(url);
        wait.forElementClickable(postButton).click();

        return this;
    }

    public AnnouncementsPage expireAnnouncement() {
        wait.forElementClickable(expireButton).click();
        Log.info("'Expire' button has been clicked");
        wait.forElementVisible(confirmationModal);
        this.clickOKButton();

        return this;
    }

    public boolean isErrorMessageVisible() {
        return isVisible(errorMessage);
    }

    /*
     * Page header in this context is the rails on the left hand side
     */
    public boolean isPageHeaderVisible() {
        return isVisible(pageHeader);
    }

    public boolean isAboutAnnouncementsVisible() {
        return isVisible(aboutAnnouncements);
    }

    public boolean isAnnouncementFormVisible() {
        return isVisible(announcementForm);
    }

    public boolean isPastAnnouncementsListVisible(){
        return isVisible(pastAnnouncementsList);
    }

}
