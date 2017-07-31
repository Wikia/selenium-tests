package com.wikia.webdriver.elements.mercury.pages.discussions;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.common.skin.Skin;
import com.wikia.webdriver.common.skin.SkinHelper;
import com.wikia.webdriver.elements.mercury.components.discussions.common.ErrorMessages;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuidelinesPage extends WikiBasePageObject {

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

  @FindBy(css = ".guidelines-text")
  private WebElement contentText;

  @FindBy(css = ".guidelines-edit-button")
  private WebElement editButton;

  @FindBy(css = ".editor-close")
  private WebElement editorClose;

  @FindBy(css = ".discussion-standalone-editor-save-button")
  private WebElement saveButton;

  @FindBy(css = ".discussion-standalone-editor-textarea")
  private WebElement guidelinesText;

  public GuidelinesPage open() {
    driver.get(urlBuilder.getUrlForWiki() + String.format(PATH));

    new SkinHelper(driver).isSkin(Skin.MERCURY);

    return this;
  }

  public DiscussionsPage clickBackToDiscussions() {
    wait.forElementClickable(backToDiscussionsButton);
    backToDiscussionsButton.click();

    return new DiscussionsPage();
  }

  private void clickEditGuidelines() {
    wait.forElementNotVisible(By.className("discussion-standalone-editor"));
    editButton.click();
  }

  public boolean isModalGuidelinesDisplayed() {
    return isElementVisible(editModal, "Edit modal");
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

  private void clickSaveButton() {
    wait.forElementClickable(saveButton);
    saveButton.click();
  }

  private void addText(String text) {
    this.guidelinesText.clear();
    this.guidelinesText.sendKeys(text);
    clickSaveButton();
  }

  private String addNewTextToGuidelines() {
    final String text = TextGenerator.createUniqueText();
    clickEditGuidelines();
    addText(text);
    return text;
  }

  private void hasTextInGuidelines(String text) {
    wait.forTextInElement(contentText, text);
    contentText.isDisplayed();
  }

  private void deleteTextFromGuidelines(String text) {
    clickEditGuidelines();
    addText(DEFAULT_GUIDELINES_TEXT);
    wait.forTextNotInElement(contentText, text);
  }

  public boolean canAddNewTextToGuidelines() {
      final String text = addNewTextToGuidelines();
      hasTextInGuidelines(text);
      deleteTextFromGuidelines(text);

      return DEFAULT_GUIDELINES_TEXT.equals(contentText.getText());
  }
}
