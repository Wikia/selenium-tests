package com.wikia.webdriver.elements.mercury.components;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Subhead extends WikiBasePageObject {

  @FindBy(css = ".sub-head--done")
  private WebElement saveButton;


  public Subhead clickPublish() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    return this;
  }

}
