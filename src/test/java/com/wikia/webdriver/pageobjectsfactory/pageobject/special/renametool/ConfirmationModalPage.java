package com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool;

import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationModalPage extends SpecialPageObject {

  By confirmationmodalContainerBy = By.cssSelector("");
  @FindBy(css = "")
  private WebElement messageTextBox;
  @FindBy(css = "#WikiaConfirmOk")
  private WebElement yesButton;
  @FindBy(css = "#WikiaConfirmCancel")
  private WebElement noButton;

  public boolean isVisible() {
    return !driver.findElements(confirmationmodalContainerBy).isEmpty();
  }

  public String getMessage() {
    return messageTextBox.getText();
  }

  public void confirm() {
    yesButton.click();
  }

  public void reject() {
    noButton.click();
  }

  public SpecialRenameUserPage accept() {
    yesButton.click();

    return new SpecialRenameUserPage();
  }
}
