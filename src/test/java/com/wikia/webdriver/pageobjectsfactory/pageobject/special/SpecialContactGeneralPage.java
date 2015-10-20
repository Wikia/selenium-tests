package com.wikia.webdriver.pageobjectsfactory.pageobject.special;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.annotations.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialContactGeneralPage extends WikiBasePageObject {

  @FindBy(css = "form#contactform p")
  private WebElement conatctFormMessage;

  public SpecialContactGeneralPage(WebDriver driver) {
    super(driver);
  }

  public SpecialContactGeneralPage open() {
    getUrl(urlBuilder.getUrlForWiki() + "wiki/Special:Contact/general");

    return this;
  }

  /**
   * True if a message for logged USER is visible on Special:Contact/general page
   * @param user
   * @return
   */
  public boolean isLoggedInUserMessageVisible(User user) {
    return conatctFormMessage.getText().contains(
        String.format("You are logged in as %s.", user.getUserName()));
  }
}
