package com.wikia.webdriver.elements.fandom.components;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FlyOut extends BasePageObject {
  @FindBy(css = "div.nav-flyout")
  private WebElement flyOut;

  public boolean hasMinOnePerVertical() {
    return itemsInVertical(".nav-flyout-content-movies") >= 1 &&
            itemsInVertical(".nav-flyout-content-games") >= 1 &&
            itemsInVertical(".nav-flyout-content-tv") >= 1;
  }

  /**
   * Return the number of elements in a flyout vertical
   * @param verticalSelector
   * @return int
   */
  private int itemsInVertical(String verticalSelector){
    String selector = verticalSelector + " .nav-flyout-column";

    return flyOut.findElements(By.cssSelector(selector)).size();
  }
}
