package com.wikia.webdriver.testcases.infoboxbuilder;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Subhead extends WikiBasePageObject {

  @FindBy(css = ".sub-head--done")
  private WebElement saveButton;

  @FindBy(css = ".sub-head--title")
  private WebElement subheadTitle;


  public Subhead clickPublish() {
    wait.forElementClickable(saveButton);
    saveButton.click();

    return this;
  }

  public Subhead clickSubheadTitle() {
    wait.forElementClickable(subheadTitle);
    subheadTitle.click();

    return this;
  }

  public String getSubheadTitle() {
    wait.forElementVisible(subheadTitle);

    return subheadTitle.getText();
  }
}
