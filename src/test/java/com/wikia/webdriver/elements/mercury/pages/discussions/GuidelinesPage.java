package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.remote.server.handler.interactions.SendKeyToActiveElement;
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

    @FindBy(css = ".editor-close")
    private WebElement editorClose;

    @FindBy(css = ".discussion-standalone-editor-save-button")
    private WebElement saveButton;

    @FindBy(css = ".discussion-standalone-editor-textarea")
    private WebElement guidelinesText;

    @FindBy(css = "modal-dialog-wrapper")
    private WebElement wrapperDiscussionsGuidelines;

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
//        wait.forElementClickable(editButton);
        wait.forElementNotVisible(By.className("discussion-standalone-editor"));
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

//    public boolean isGuidelinesHeroUnitDisplayed() {
//        try {
//            wait.forElementVisible(heroUnit);
//
//            return heroUnit.isDisplayed();
//        } catch (TimeoutException e) {
//            PageObjectLogging.logInfo("heroUnit is not displayed", e);
//
//            return false;
//        }
//    }

    public boolean isGuidelinesHeroUnitDisplayed() {
        return isSthg(heroUnit);
    }

    private boolean isSthg(WebElement element) {
        boolean result = false;

        try {
            wait.forElementVisible(element);
            result = element.isDisplayed();
        } catch (TimeoutException e) {
            PageObjectLogging.logInfo("heroUnit is not displayed", e);
        }

        return result;
    }

    public boolean isGuidelinesTextDisplayed() {
        return isSthg(contentText);
    }

//    public boolean isGuidelinesTextDisplayed() {
//        try {
//            wait.forElementVisible(contentText);
//
//            return contentText.isDisplayed();
//        } catch (TimeoutException e) {
//            PageObjectLogging.logInfo("contentText is not displayed", e);
//
//            return false;
//        }
//    }

    public boolean isEditButtonDisplayed() {
        return isSthg(editButton);
    }

    /*public boolean isEditButtonDisplayed() {
        try {
            wait.forElementVisible(editButton);

            return editButton.isDisplayed();
        } catch (TimeoutException e) {
            PageObjectLogging.logInfo("Edit button is not displayed", e);

            return false;
        }
    }*/

    public void clickSaveButton() {
        wait.forElementClickable(saveButton);
        saveButton.click();
    }

    public void guidelinesAddNewText(String contentText) {
        driver.findElement(By.className("discussion-standalone-editor-textarea")).sendKeys(contentText);
        clickSaveButton();
    }

    public void verifyContentNevTextInGuidelines(String content) {
        wait.forTextInElement(contentText, content);
    }

    public void testNewTextToGuidelines() {
        String guidelinesText = TextGenerator.createUniqueText();
        clickEditGuidelines();
        guidelinesAddNewText(guidelinesText);
        verifyContentNevTextInGuidelines(guidelinesText);
        contentText.isDisplayed();

        clickEditGuidelines();
        String replace = driver.findElement(By.className("discussion-standalone-editor-textarea")).getText()
            .replace(guidelinesText, "");
        guidelinesAddNewText(replace);
    }

     public boolean isNewGuidelinesTextDisplayed() {

        try {
            testNewTextToGuidelines();
            return guidelinesText.isDisplayed();
        } catch (TimeoutException e) {
            PageObjectLogging.logInfo("New text in Guidelines is not displayed", e);

            return false;
        }
     }
}
