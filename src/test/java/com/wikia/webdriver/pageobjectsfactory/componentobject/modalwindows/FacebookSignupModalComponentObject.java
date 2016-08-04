package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

public class FacebookSignupModalComponentObject extends WikiBasePageObject {

  @FindBy(css = "button[name='__CONFIRM__']")
  private WebElement appTermsConfirmButton;
  @FindBy(css = "#signupUsername")
  private WebElement usernameField;
  @FindBy(css = "#signupPassword")
  private WebElement passwordField;
  @FindBy(css = "#signupEmail")
  private WebElement emailField;
  @FindBy(css = ".UserLoginFacebookRight input[name='username']")
  private WebElement existingUsernameField;
  @FindBy(css = ".UserLoginFacebookRight input[name='password']")
  private WebElement existingPasswordField;
  @FindBy(css = "#signupSubmit")
  private WebElement registerButton;
  @FindBy(css = ".UserLoginFacebookRight input[type='submit']")
  private WebElement loginExistingButton;
  @FindBy(css = "#u_0_4")
  private WebElement editInfoProvided;
  @FindBy(xpath = "//input[@type='checkbox'][@value='email']/..")
  private WebElement emailCheckbox;

  public FacebookSignupModalComponentObject(WebDriver driver) {
    super();
  }

  public void acceptWikiaAppPolicy() {
    new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
      @Nullable
      @Override
      public Boolean apply(WebDriver input) {
        return input.getWindowHandles().size() > 1;
      }
    });

    Object[] handles = driver.getWindowHandles().toArray();

    driver.switchTo().window(handles[1].toString());
    wait.forElementVisible(By.cssSelector("button[name='__CONFIRM__']"));
    appTermsConfirmButton.click();
    PageObjectLogging.log("acceptWikiaAppPolicy", "confirmed wikia apps privacy policy", true);
    driver.switchTo().window(handles[0].toString());
  }

  public void acceptWikiaAppPolicyNoEmail() {

    new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
      @Nullable
      @Override
      public Boolean apply(WebDriver input) {
        return input.getWindowHandles().size() > 1;
      }
    });

    Object[] handles = driver.getWindowHandles().toArray();

    driver.switchTo().window(handles[1].toString());
    wait.forElementVisible(By.cssSelector("button[name='__CONFIRM__']"));
    appTermsConfirmButton.click();
    driver.switchTo().window(handles[0].toString());
  }

  public void typeUserName(String userName) {
    usernameField.sendKeys(userName);
    PageObjectLogging.log("typeUserName", "username " + userName + " typed into the field", true);
  }

  public void typePassword(String password) {
    wait.forElementClickable(passwordField);
    passwordField.sendKeys(password);
    PageObjectLogging.log("typePassword", "password typed into the field", true);
  }

  public void typeEmail(String email) {
    wait.forElementVisible(emailField);
    emailField.sendKeys(email);
    PageObjectLogging.log("typeEmail", "email typed into the field", true);
  }

  public void clickRegister() {
    wait.forElementVisible(registerButton);
    registerButton.click();
    PageObjectLogging.log("clickRegister", "Create account button clicked", true);
    waitForElementNotVisibleByElement(registerButton);
  }

  public void createAccountNoEmail(String email, String userName, String password) {
    acceptWikiaAppPolicyNoEmail();
    waitForValueToBePresentInElementsAttributeByElement(emailField, "value", email);

    typeUserName(userName);
    typePassword(password);
    clickRegister();
  }

}
