package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.discussions.common.Editor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.GuidelinesEditor;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuidelinesPage extends WikiBasePageObject implements Editor {

  private static final String DEFAULT_GUIDELINES_TEXT = "Discussion Guidelines";

  private GuidelinesEditor editor = new GuidelinesEditor();

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  private static final String PATH = "/d/g";

  @FindBy(css = ".side-button.back-button")
  private WebElement backToDiscussionsButton;

  @FindBy(css = ".standalone-content")
  private WebElement editModal;

  @FindBy(css = ".discussion-left-rail__header")
  private WebElement leftRailHeader;

  @FindBy(css = ".guidelines-text")
  private WebElement contentText;

  @FindBy(css = ".guidelines-edit-button")
  private WebElement editButton;

  @FindBy(css = ".editor-close")
  private WebElement editorClose;

  @FindBy(css = ".discussion-standalone-editor-save-button")
  private WebElement saveButton;

  @FindBy(css = ".editor-close")
  private WebElement cancelButton;

  @FindBy(css = ".discussion-standalone-editor-textarea")
  private WebElement guidelinesText;

  public GuidelinesPage open() {
    driver.get(String.format("%s%s", urlBuilder.getUrlForWiki(), PATH));
    return this;
  }

  public DiscussionsPage clickBackToDiscussions() {
    waitAndClick(backToDiscussionsButton);
    return new DiscussionsPage();
  }

  private void clickEditGuidelines() {
    wait.forElementNotVisible(By.className("discussion-standalone-editor"));
    editButton.click();
  }

  private boolean isElementVisible(WebElement element, String elementName) {
    boolean result = false;

    try {
      wait.forElementVisible(element);
      result = element.isDisplayed();
    } catch (TimeoutException e) {
      PageObjectLogging.logInfo(elementName + " is not displayed", e);
    }

    return result;
  }

  public boolean isGuidelinesImageAreaDisplayed() {
    return isElementVisible(leftRailHeader, "Guidelines image");
  }

  public boolean isEditButtonDisplayed() {
    return isElementVisible(editButton, "Edit button");
  }

  @Override
  public boolean isSubmitButtonActive() {
    return editor.isSubmitButtonActive();
  }

  @Override
  public Editor clickSubmitButton() {
    return editor.clickSubmitButton();
  }

  @Override
  public Editor clickCancelButton() {
    return editor.clickCancelButton();
  }

  @Override
  public Editor addTextWith(String text) {
    return editor.addTextWith(text);
  }

  @Override
  public Editor clearText() {
    return editor.clearText();
  }

  private String addNewTextToGuidelines() {
    final String text = TextGenerator.createUniqueText();
    clickEditGuidelines();
    addTextWith(text);
    return text;
  }

  private void hasTextInGuidelines(String text) {
    wait.forTextInElement(contentText, text);
    contentText.isDisplayed();
  }

  private void deleteTextFromGuidelines(String text) {
    clickEditGuidelines();
    addTextWith(DEFAULT_GUIDELINES_TEXT);
    wait.forTextNotInElement(contentText, text);
  }

  public boolean canUpdateGuidelinesContent() {
      final String text = addNewTextToGuidelines();
      hasTextInGuidelines(text);
      deleteTextFromGuidelines(text);

      return DEFAULT_GUIDELINES_TEXT.equals(contentText.getText());
  }
}
