package com.wikia.webdriver.elements.mercury.components;


import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Subhead extends WikiBasePageObject {

  @FindBy(css = ".sub-head--done")
  private WebElement saveButton;

  @FindBy(css = ".sub-head--title")
  private WebElement topBarTemplateTitle;

  public Subhead clickPublish() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    return this;
  }

  public Subhead clickTopBarTemplateTitle() {
    wait.forElementClickable(topBarTemplateTitle);
    topBarTemplateTitle.click();

    return this;
  }

}
