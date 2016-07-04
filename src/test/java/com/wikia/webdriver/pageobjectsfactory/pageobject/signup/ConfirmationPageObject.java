package com.wikia.webdriver.pageobjectsfactory.pageobject.signup;

import com.wikia.webdriver.common.core.MailFunctions;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.createnewwiki.CreateNewWikiPageObjectStep1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPageObject extends BasePageObject {

  @FindBy(css = "div.UserConfirm input[name='username']")
  private WebElement userNameField;
  @FindBy(css = "div.UserConfirm input[name='password']")
  private WebElement passwordField;
  @FindBy(css = "div.UserConfirm input[type='submit']")
  private WebElement confirmationButton;

  public ConfirmationPageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public void typeInUserName(String userName) {
    userNameField.sendKeys(userName);
    PageObjectLogging.log("typeInUserName ", "user name field populated", true, driver);
  }

  public void typeInPassword(String password) {
    passwordField.sendKeys(password);
    PageObjectLogging.log("typeInUserPassword ", "password field populated", true, driver);
  }

  public UserProfilePageObject clickSubmitButton(String email, String password) {
    MailFunctions.deleteAllEmails(email, password);
    scrollAndClick(confirmationButton);
    PageObjectLogging.log("submit button clicked ", "submit button clicked", true, driver);
    return new UserProfilePageObject(driver);
  }

  public CreateNewWikiPageObjectStep1 CNWSubmitButton(String email, String password) {
    MailFunctions.deleteAllEmails(email, password);
    scrollAndClick(confirmationButton);
    PageObjectLogging.log("submit button clicked ", "submit button clicked", true, driver);
    return new CreateNewWikiPageObjectStep1(driver);
  }
}
