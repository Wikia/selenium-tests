package com.wikia.webdriver.pageobjectsfactory.pageobject;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.mercury.pages.discussions.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AnnouncementsPage extends BasePage {

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
        Assertion.assertTrue(isVisible(confirmationModal), "confirmation modal is visible");
        this.clickOKButton();

        return this;
    }

}
