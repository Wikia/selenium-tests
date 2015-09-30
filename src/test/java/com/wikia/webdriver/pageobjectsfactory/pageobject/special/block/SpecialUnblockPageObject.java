package com.wikia.webdriver.pageobjectsfactory.pageobject.special.block;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class SpecialUnblockPageObject extends WikiBasePageObject {

  @FindBy(css = "#mw-input-wpTarget")
  private WebElement userNameField;
  @FindBy(css = ".mw-htmlform-submit")
  private WebElement submitButton;
  @FindBy(xpath = "//h1[contains(text(), 'Unblock')]")
  private WebElement unblockedUserHead;

  public SpecialUnblockPageObject(WebDriver driver) {
    super(driver);
  }

  private void typeInUserName(String userName) {
    wait.forElementVisible(userNameField);
    userNameField.sendKeys(userName);
    LOG.success("typeInUserName", userName + "typed into username field");
  }

  private void clickSubmitButton() {
    wait.forElementVisible(submitButton);
    scrollAndClick(submitButton);
    LOG.success("clickSubmitButton", "submit button clicked");
  }

  public void unblockUser(String userName) {
    typeInUserName(userName);
    clickSubmitButton();
  }

  public void verifyUnblockMessage(String userName) {
    wait.forElementVisible(unblockedUserHead);
    wait.forElementVisible(By.xpath("//div[@id='mw-content-text']//a[@href='/wiki/User:" + userName
        + "' and contains(text(), '" + userName + "')]"));
    wait.forElementVisible(By
        .xpath("//div[@id='mw-content-text']//p[contains(text(), 'has been unblocked')]"));
    LOG.success("verifyUnblockMessage", "unblock user messages verified", true);
  }

}
