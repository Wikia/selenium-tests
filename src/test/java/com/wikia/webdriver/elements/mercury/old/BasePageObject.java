package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePageObject extends WikiBasePageObject {

  public BasePageObject(WebDriver driver) {
    super(driver);
  }

  private enum Settings {
    TIME_OUT_IN_SEC(5),
    CHECK_OUT_IN_MILLI_SEC(1000);

    private int value;

    Settings(int value) {
      this.value = value;
    }
  }

  public boolean isElementVisible(WebElement element) {
    try {
      wait.forElementVisible(element, Settings.TIME_OUT_IN_SEC.value,
                             Settings.CHECK_OUT_IN_MILLI_SEC.value);
    } catch (TimeoutException e) {
      return false;
    }
    return true;
  }
}
