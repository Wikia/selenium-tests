package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ModalDialog extends WikiBasePageObject {

  @FindBy(css = ".modal-dialog")
  private WebElement modal;

  @FindBy(css = ".modal-dialog div.modal-bottom-row > button:nth-child(1)")
  private WebElement saveChangesButton;

  @FindBy(css = ".modal-dialog div.modal-bottom-row > button:nth-child(2)")
  private WebElement dropChangesButton;

  public boolean isPresent() {
    wait.forElementVisible(modal);
    return modal.isDisplayed();
  }

  public void clickDropChangesButton() {
    wait.forElementClickable(dropChangesButton);
    dropChangesButton.click();
  }

  public void clickSaveChangesButton() {
    wait.forElementClickable(saveChangesButton);
    saveChangesButton.click();
  }
}
