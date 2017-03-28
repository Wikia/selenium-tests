package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FormError {

  @FindBy(css = "form .error")
  private static WebElement error;

  public static String getError() {
    return error.getText();
  }
}
