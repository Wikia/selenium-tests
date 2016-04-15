package com.wikia.webdriver.elements.mercury.components;


import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Spinner extends WikiBasePageObject {

  @FindBy(css = ".infobox-builder-spinner")
  private WebElement savingSpinner;

  public boolean isSpinnerPresent() {
    wait.forElementVisible(savingSpinner);

    return savingSpinner.isDisplayed();
  }

}
