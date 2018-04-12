package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuidelinesPage extends BasePage {

  private static final String DEFAULT_GUIDELINES_TEXT = "Discussion Guidelines";

  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();

  private static final String PATH = "/d/g";

  @FindBy(css = ".side-button.back-button")
  private WebElement backToDiscussionsButton;

  @FindBy(css = ".standalone-content")
  private WebElement editModal;

  @FindBy(css = ".discussion-left-rail__header")
  private WebElement leftRailHeader;

  @FindBy(css = ".editor-input-label-wrapper textarea")
  private WebElement guidelinesEditorTextarea;

  @FindBy(css = ".guidelines-edit-button")
  private WebElement editButton;

  @FindBy(css = ".editor-close")
  private WebElement editorClose;

  @FindBy(css = ".discussion-standalone-editor button[type='submit']")
  private WebElement saveButton;

  @FindBy(css = ".guidelines-text")
  private WebElement guidelinesText;

  public GuidelinesPage open() {
    driver.get(getUrlWithCacheBuster(String.format("%s%s", urlBuilder.getUrlForWiki(), PATH)));
    return this;
  }

  public DiscussionsPage clickBackToDiscussions() {
    waitAndClick(backToDiscussionsButton);
    return new DiscussionsPage();
  }

  private void clickEditGuidelines() {
    wait.forElementNotVisible(By.className("discussion-standalone-editor"));
    wait.forElementClickable(editButton);
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

  public String getGuidelinesText() {
    wait.forElementVisible(guidelinesText);

    return guidelinesText.getText();
  }

  public boolean isGuidelinesImageAreaDisplayed() {
    return isElementVisible(leftRailHeader, "Guidelines image");
  }

  public boolean isEditButtonDisplayed() {
    return isElementVisible(editButton, "Edit button");
  }

  private void clickSaveButton() {
    wait.forElementClickable(saveButton);
    saveButton.click();
  }

  private void addText(String text) {
    this.guidelinesEditorTextarea.clear();
    this.guidelinesEditorTextarea.sendKeys(text);
    clickSaveButton();
    waitForLoadingSpinner();
  }

  private String addNewTextToGuidelines() {
    final String text = TextGenerator.createUniqueText();
    clickEditGuidelines();
    addText(text);
    return text;
  }

  private void hasTextInGuidelines(String text) {
    wait.forTextInElement(guidelinesText, text);
    guidelinesText.isDisplayed();
  }

  private void deleteTextFromGuidelines(String text) {
    clickEditGuidelines();
    addText(DEFAULT_GUIDELINES_TEXT);
    wait.forTextNotInElement(guidelinesText, text);
  }

  public boolean canUpdateGuidelinesContent() {
      final String text = addNewTextToGuidelines();
      hasTextInGuidelines(text);
      deleteTextFromGuidelines(text);

      return DEFAULT_GUIDELINES_TEXT.equals(guidelinesText.getText());
  }
}
