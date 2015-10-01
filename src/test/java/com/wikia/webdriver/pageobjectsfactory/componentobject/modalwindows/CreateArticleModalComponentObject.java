package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

/**
 * @author Bogna 'bognix' Knychala
 */
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

  public CreateArticleModalComponentObject(WebDriver driver) {
    super(driver);
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
    LOG.result("PageCreated", "Page with given title created", true);
  }

  public void verifyMessageAboutBlockPresent() {
    wait.forElementVisible(phalanxBlockMessageContainer);
    wait.forTextInElement(phalanxBlockMessageContainer, PageContent.PHALANX_BLOCK_TITLE_MESSAGE);
    LOG.success("MessageAboutBlockPresent", "Message about block present", true);
  }

  /**
   * Checks layout's radiobutton accroding to layout type given as param Layout can have values:
   * standard - layout with video and image placeholders top - layout for top10List page blank -
   * blank page's layout
   *
   * @param String layout
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

  public void verifyCreateNewArticleModal() {
    wait.forElementVisible(addAPageButton);
  }

}
