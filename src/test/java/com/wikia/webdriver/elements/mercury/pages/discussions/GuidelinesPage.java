package com.wikia.webdriver.elements.mercury.pages.discussions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import lombok.Getter;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuidelinesPage extends WikiBasePageObject {

    @Getter(lazy = true)
    private final ErrorMessages errorMessages = new ErrorMessages();

    private static final String PATH = "/d/g";

    @FindBy(css = ".side-button.back-button")
    private WebElement backToDiscussionsButton;

    @FindBy(css = ".standalone-content")
    private WebElement editModal;

    @FindBy(css = ".discussion-hero-unit-fade")
    private WebElement heroUnit;

    @FindBy(css = ".guidelines-text")
    private WebElement contentText;

    @FindBy(css = ".guidelines-edit-link")
    private WebElement editButton;

    public GuidelinesPage open() {
        driver.get(urlBuilder.getUrlForWiki() + String.format(PATH));
        return this;
    }

    public DiscussionsPage clickBackToDiscussions() {
        wait.forElementClickable(backToDiscussionsButton);
        backToDiscussionsButton.click();

        return new DiscussionsPage();
    }

    public void clickEditGuidelines() {
        wait.forElementClickable(editButton);
        editButton.click();
    }

    public boolean isModalGuidelinesDisplayed() {
        try {
            wait.forElementVisible(editModal);

            return editModal.isDisplayed();
        } catch (TimeoutException e) {
            PageObjectLogging.logInfo("editModal is not displayed", e);

            return false;
        }
    }

    public boolean isGuidelinesHeroUnitDisplayed() {
        try {
            wait.forElementVisible(heroUnit);

            return heroUnit.isDisplayed();
        } catch (TimeoutException e) {
            PageObjectLogging.logInfo("heroUnit is not displayed", e);

            return false;
        }
    }

    public boolean isGuidelinesTextDisplayed() {
        try {
            wait.forElementVisible(contentText);

            return contentText.isDisplayed();
        } catch(TimeoutException e) {
            PageObjectLogging.logInfo("contentText is not displayed", e);

            return false;
        }
    }

    public boolean isEditButtonDisplayed() {
        try {
            wait.forElementVisible(editButton);

            return editButton.isDisplayed();
        } catch(TimeoutException e) {
            PageObjectLogging.logInfo("Edit button is not displayed", e);

            return false;
        }
    }

}