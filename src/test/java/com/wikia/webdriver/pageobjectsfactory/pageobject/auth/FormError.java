package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormError extends BasePageObject {

  @FindBy(css = "form .error")
  private WebElement error;

  public String getError() {
    return wait.forElementVisible(error).getText();
  }
}
