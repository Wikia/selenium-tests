package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Rodriuki on 16/06/15.
 */
public class SpecialWhatLinksHerePageObject extends SpecialPageObject {
  @FindBy(css = "input[name=target]")
  private WebElement pageInputField;
  @FindBy(css = ".namespaceselector + input[type=submit]")
  private WebElement showButton;

  public void clickShowbutton() {
    waitForElementByElement(showButton);
    showButton.click();
  }



}
