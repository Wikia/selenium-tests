package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateArticleModalComponentObject extends WikiBasePageObject {

  @FindBy(css = "#wpCreatePageDialogTitle")
  private WebElement titleInput;
  @FindBy(css = "#CreatePageDialogToplist")
  private WebElement topListRadioButton;
  @FindBy(css = "#CreatePageDialogFormat")
  private WebElement standardRadioButton;
  @FindBy(css = "#CreatePageDialogBlank")
  private WebElement blankRadioButton;
  @FindBy(css = ".button.normal.primary")
  private WebElement createPageButton;
  @FindBy(css = "#createPageErrorMsg")
  private WebElement phalanxBlockMessageContainer;
  @FindBy(css = "button[data-event=create]")
  private WebElement addAPageButton;
  @FindBy(css = "#CreatePageModalDialog .close")
  private WebElement closeButton;

  public CreateArticleModalComponentObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public void createPageWithBlankLayout(String title) {
    createPage(title, "blank");
  }

  private void createPage(String title, String layout) {
    wait.forElementVisible(titleInput);
    titleInput.sendKeys(title);
    chooseLayout(layout);
    wait.forElementVisible(createPageButton);
    scrollAndClick(createPageButton);
    PageObjectLogging.log(
        "PageCreated",
        "Page with given title created",
        true
    );
  }

  public void verifyMessageAboutBlockPresent() {
    wait.forElementVisible(phalanxBlockMessageContainer);
    wait.forTextInElement(
        phalanxBlockMessageContainer, PageContent.PHALANX_BLOCK_TITLE_MESSAGE
    );
    PageObjectLogging.log(
        "MessageAboutBlockPresent",
        "Message about block present",
        true,
        driver
    );
  }

  /**
   * Checks layout's radiobutton accroding to layout type given as param Layout can have values:
   * standard - layout with video and image placeholders top - layout for top10List page blank -
   * blank page's layout
   */
  private void chooseLayout(String layout) {
    if ("standard".equals(layout)) {
      wait.forElementClickable(standardRadioButton);
      scrollAndClick(standardRadioButton);
      return;
    }
    if ("blank".equals(layout)) {
      wait.forElementClickable(blankRadioButton);
      scrollAndClick(blankRadioButton);
      return;
    }
    if ("top".equals(layout)) {
      wait.forElementClickable(topListRadioButton);
      scrollAndClick(topListRadioButton);
    }
  }

  public boolean isCreateNewArticleModalVisible() {
    try {
      wait.forElementVisible(addAPageButton);
      return true;
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo(e.getMessage());
      return false;
    }
  }

  public void close() {
    wait.forElementClickable(closeButton).click();
  }
}
