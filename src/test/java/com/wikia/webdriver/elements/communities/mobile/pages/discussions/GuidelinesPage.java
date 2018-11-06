package com.wikia.webdriver.elements.communities.mobile.pages.discussions;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.elements.communities.mobile.components.discussions.common.ErrorMessages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GuidelinesPage extends BasePage {

  private static final String PATH = "/d/g";
  @Getter(lazy = true)
  private final ErrorMessages errorMessages = new ErrorMessages();
  @FindBy(css = ".side-button.back-button")
  private WebElement backToDiscussionsButton;

  @FindBy(css = ".standalone-content")
  private WebElement editModal;

  @FindBy(css = ".discussion-left-rail__header")
  private WebElement leftRailHeader;

  @FindBy(css = ".editor-textarea.is-description")
  private List<WebElement> guidelinesEditorTextarea;

  @FindBy(css = ".guidelines-edit-button")
  private WebElement editButton;

  @FindBy(css = ".editor-close")
  private WebElement editorClose;

  @FindBy(css = ".discussion-standalone-editor button[type='submit']")
  private WebElement saveButton;

  @FindBy(css = ".guidelines-text")
  private WebElement guidelinesText;

  public GuidelinesPage open() {
    driver.get(getUrlWithCacheBuster(String.format("%s%s", urlBuilder.getUrl(), PATH)));

    return this;
  }

  public DiscussionsPage clickBackToDiscussions() {
    scrollTo(backToDiscussionsButton);
    waitAndClick(backToDiscussionsButton);

    return new DiscussionsPage();
  }

  private void clickEditGuidelines() {
    wait.forElementNotVisible(By.className("discussion-standalone-editor"));
    wait.forElementClickable(editButton);
    editButton.click();
  }

  public String getGuidelinesText() {
    wait.forElementVisible(guidelinesText);

    return guidelinesText.getText();
  }

  public boolean isGuidelinesImageAreaDisplayed() {
    return isElementDisplayed(leftRailHeader);
  }

  public boolean isEditButtonDisplayed() {
    return isElementDisplayed(editButton);
  }

  private void clickSaveButton() {
    wait.forElementClickable(saveButton);
    saveButton.click();
  }

  private void appendText(String text) {
    this.guidelinesEditorTextarea.get(0).sendKeys(text);
    clickSaveButton();
    waitForLoadingSpinner();
  }

  public GuidelinesPage clearAndAddUniqueText(String text) {
    clickEditGuidelines();
    this.guidelinesEditorTextarea.clear();
    appendText(text);

    return this;
  }

  public boolean doesGuidelinesContainsText(String text) {
    boolean isPresent = false;
    try {
      wait.forTextInElement(guidelinesText, text);
      isPresent = true;
    } catch (Exception e) {
      Log.log("Guidelines should contains text: " + text, e, false);
    }

    return isPresent;
  }
}
