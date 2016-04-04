package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalDialog extends WikiBasePageObject {

  @FindBy(css = ".modal-dialog")
  private WebElement modal;

  @FindBy(css = ".modal-dialog div.modal-bottom-row > button:nth-child(1)")
  private WebElement firstButton;

  @FindBy(css = ".modal-dialog div.modal-bottom-row > button:nth-child(2)")
  private WebElement secondButton;

  @FindBy(css = "#editTemplateTitle")
  private WebElement editTemplateTitleInput;

  @FindBy(css = ".text-field-error-message")
  private WebElement errorMessage;

  public boolean isPresent() {
    wait.forElementVisible(modal);
    return modal.isDisplayed();
  }

  public boolean isEditTemplateTitlePresent() {
    wait.forElementVisible(editTemplateTitleInput);
    return editTemplateTitleInput.isDisplayed();
  }

  public void insertTemplateTitle(String title) {
    wait.forElementClickable(editTemplateTitleInput);
    editTemplateTitleInput.sendKeys(title);
  }

  public boolean isErrorMessagePresent() {
    wait.forElementVisible(errorMessage);
    return errorMessage.isDisplayed();
  }

  public void clickSecondButton() {
    wait.forElementClickable(secondButton);
    secondButton.click();
  }

  public void clickFirstButton() {
    wait.forElementClickable(firstButton);
    firstButton.click();
  }
}
