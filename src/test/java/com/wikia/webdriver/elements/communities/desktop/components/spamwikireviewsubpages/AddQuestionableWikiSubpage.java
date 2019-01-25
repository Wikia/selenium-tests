package com.wikia.webdriver.elements.communities.desktop.components.spamwikireviewsubpages;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddQuestionableWikiSubpage extends BasePageObject {

  @FindBy(css = "#ids")
  private WebElement addIdsTextArea;

  @FindBy(xpath = "//button[@type='submit']")
  private WebElement submitIdsButton;

  public boolean isSubmitIdsButtonDisplayed(){
    return submitIdsButton.isDisplayed();
  }

  public boolean isAddIdsTextAreaDisplayed() {
    return addIdsTextArea.isDisplayed();
  }
}
