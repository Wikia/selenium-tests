package com.wikia.webdriver.pageobjectsfactory.pageobject.special.block;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialUnblockPage extends WikiBasePageObject {
  public static final String SPECIAL_UNBLOCK_PATH = "Special:Unblock";

  @FindBy(css = "#mw-input-wpTarget")
  private WebElement userNameField;
  @FindBy(css = ".mw-htmlform-submit")
  private WebElement submitButton;
  @FindBy(xpath = "//h1[contains(text(), 'Unblock')]")
  private WebElement unblockedUserHead;

  public SpecialUnblockPage open() {
    getUrl(urlBuilder.getUrlForPath(SPECIAL_UNBLOCK_PATH));
    PageObjectLogging.log("openSpecialUnblockPage", "special unblock page opened", true);

    return this;
  }

  private void typeInUserName(String userName) {
    wait.forElementVisible(userNameField);
    userNameField.sendKeys(userName);
    PageObjectLogging.log("typeInUserName", userName + "typed into username field", true);
  }

  private void clickSubmitButton() {
    wait.forElementVisible(submitButton);
    scrollAndClick(submitButton);
    PageObjectLogging.log("clickSubmitButton", "submit button clicked", true);
  }

  public void unblockUser(String userName) {
    typeInUserName(userName);
    clickSubmitButton();
  }

  public void verifyUnblockMessage(String userName) {
    wait.forElementVisible(unblockedUserHead);
    wait.forElementVisible(By.xpath("//div[@id='mw-content-text']//a[@href='/wiki/User:" + userName
        + "' and contains(text(), '" + userName + "')]"));
    wait.forElementVisible(
        By.xpath("//div[@id='mw-content-text']//p[contains(text(), 'has been unblocked')]"));
    PageObjectLogging.log("verifyUnblockMessage", "unblock user messages verified", true, driver);
  }
}
