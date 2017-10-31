package com.wikia.webdriver.pageobjectsfactory.pageobject.special.renametool;

import com.wikia.webdriver.pageobjectsfactory.pageobject.special.SpecialPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationModalPage extends SpecialPageObject {

  //TODO: Move it to components
  @FindBy(css = "")
  private WebElement messageTextBox;
  @FindBy(css = "input[value=\"Yes\"]")
  private WebElement yesButton;
  @FindBy(css = "input[value=\"No\"]")
  private WebElement noButton;
  By confirmationmodalContainerBy = By.cssSelector("");

  public ConfirmationModalPage(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

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
}
