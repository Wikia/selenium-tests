package com.wikia.webdriver.PageObjectsFactory.ComponentObject.ModalWindows;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Logging.PageObjectLogging;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    @FindBy (css = ".wikia-button.createpage")
    private WebElement createPageButton;
    @FindBy (css = "#createPageErrorMsg")
    private WebElement phalanxBlockMessageContainer;

    public CreateArticleModalComponentObject(WebDriver driver, String Domain) {
        super(driver, Global.DOMAIN);
        PageFactory.initElements(driver, this);
    }

    public void createPageWithStandardLayout(String title) {
        createPage(title, "standard");
    }

    private void createPage(String title, String layout) {
        waitForElementByElement(titleInput);
        sendKeys(titleInput, title);
        chooseLayout(layout);
        waitForElementByElement(createPageButton);
        clickAndWait(createPageButton);
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
            clickAndWait(standardRadioButton);
            return;
        }
        if (layout.equals("blank")) {
            clickAndWait(blankRadioButton);
            return;
        }
        if (layout.equals("top")) {
            clickAndWait(topListRadioButton);
        }
    }
}
