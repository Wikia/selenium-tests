package com.wikia.webdriver.pageobjectsfactory.pageobject.special.block;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SpecialUnblockPage extends WikiBasePageObject {
  private static final String SPECIAL_UNBLOCK_PATH = "Special:Unblock";

  @FindBy(id = "mw-input-wpTarget")
  private WebElement userNameField;
  @FindBy(className = "mw-htmlform-submit")
  private WebElement submitButton;

  public SpecialUnblockPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_UNBLOCK_PATH));

    return this;
  }

  public void unblockUser(String userName) {
    userNameField.sendKeys(userName);
    submitButton.click();
  }
}
