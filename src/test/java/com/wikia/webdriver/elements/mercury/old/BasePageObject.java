package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.List;

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

  /**
   * Verify if element inside element of the provided list has given text. This method assumes list
   * element is not the final target of verification This method assumes list element is the parent
   * of target element
   *
   * @param list           List that contains the parent element
   * @param elementLocator Locator of target element, that is child of its parent element
   * @param text           Text to be compared
   */
  protected void verifyTextInListElements(List<WebElement> list, By elementLocator, String text) {
    WebElement innerElem;
    for (WebElement elem : list) {
      wait.forElementVisible(elem);
      innerElem = elem.findElement(elementLocator);
      if (innerElem.getText().equals(text)) {
        return;
      }
    }
    throw new WebDriverException(getNoTextInListErrorMessage(text));
  }
  
  private String getNoTextInListErrorMessage(String text) {
    return "element with text " + text + "not found in the list";
  }

}
