package com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;

/**
 *
 * @author Bogna 'bognix' Knychala
 */
public class CreateArticleModalComponentObject extends WikiBasePageObject {

    @FindBy (css = "#wpCreatePageDialogTitle")
    private WebElement titleInput;
    @FindBy (css = "#CreatePageDialogToplist")
    private WebElement topListRadioButton;
    @FindBy (css = "#CreatePageDialogFormat")
    private WebElement standardRadioButton;
    @FindBy (css = "#CreatePageDialogBlank")
    private WebElement blankRadioButton;
    @FindBy (css = ".button.normal.primary")
    private WebElement createPageButton;
    @FindBy (css = "#createPageErrorMsg")
    private WebElement phalanxBlockMessageContainer;

    public CreateArticleModalComponentObject(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void createPageWithStandardLayout(String title) {
        createPage(title, "standard");
    }

    public void createPageWithBlankLayout(String title) {
    	createPage(title, "blank");
    }

    private void createPage(String title, String layout) {
        waitForElementByElement(titleInput);
        sendKeys(titleInput, title);
        chooseLayout(layout);
        waitForElementByElement(createPageButton);
        scrollAndClick(createPageButton);
        PageObjectLogging.log(
            "PageCreated",
            "Page with given title created",
            true
        );
    }

    public void verifyMessageAboutBlockPresent() {
        waitForElementByElement(phalanxBlockMessageContainer);
        waitForTextToBePresentInElementByElement(
            phalanxBlockMessageContainer, PageContent.phalanxBlockTitleMessage
        );
        PageObjectLogging.log(
            "MessageAboutBlockPresent",
            "Message about block present",
            true,
            driver
        );
    }

    /**
     * Checks layout's radiobutton accroding to layout type given as param
     * Layout can have values:
     *  standard - layout with video and image placeholders
     *  top - layout for top10List page
     *  blank - blank page's layout
     *
     * @param String layout
     */
    private void chooseLayout(String layout) {
        if (layout.equals("standard")) {
            scrollAndClick(standardRadioButton);
            return;
        }
        if (layout.equals("blank")) {
            scrollAndClick(blankRadioButton);
            return;
        }
        if (layout.equals("top")) {
            scrollAndClick(topListRadioButton);
        }
    }
}
