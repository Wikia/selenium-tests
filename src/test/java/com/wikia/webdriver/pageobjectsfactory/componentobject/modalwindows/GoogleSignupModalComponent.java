package com.wikia.webdriver.pageobjectsfactory.componentobject.modalwindows;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;

public class GoogleSignupModalComponent extends WikiBasePageObject {

  @FindBy(css = "button[name='__CONFIRM__']")
  private WebElement appTermsConfirmButton;

  @FindBy(css = "#signupUsername")
  private WebElement usernameField;

  @FindBy(css = "#signupEmail")
  private WebElement emailField;

  @FindBy(css = "#signupSubmit")
  private WebElement registerButton;

  @FindBy(css = "#facebookRegistrationForm")
  private WebElement facebookRegistrationForm;

  @FindBy(css = "#signupBirthDate")
  private WebElement birthdateContainer;

  @FindBy(css = "input.birth-month")
  private WebElement birthMonthField;

  @FindBy(css = ".birth-day")
  private WebElement birthDayField;

  @FindBy(css = ".birth-month")
  private WebElement birthYearField;

  public GoogleSignupModalComponent() {
    super();
  }

  public void acceptWikiaAppPolicyNoEmail() {

    new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
      @Nullable
      @Override
      public Boolean apply(WebDriver input) {
        return input.getWindowHandles().size() > 1;
      }
    });

    //switch to Google modal;
    Object[] handles = driver.getWindowHandles().toArray();
    driver.switchTo().window(handles[1].toString());

    wait.forElementVisible(By.cssSelector("button[name='__CONFIRM__']"));
    appTermsConfirmButton.click();

    //switch back to Wikia auth modal
    driver.switchTo().window(handles[0].toString());
  }

  public void typeEmail(String email) {
    wait.forElementClickable(emailField);
    emailField.sendKeys(email);
    Log.log("typeEmail", "email " + email + " typed into the field", true);
  }

  public void typeUserName(String userName) {
    wait.forElementClickable(usernameField);
    usernameField.sendKeys(userName);
    Log.log("typeUserName", "username " + userName + " typed into the field", true);
  }

  public void typeBirthday(int month, int day, int year) {
    wait.forElementClickable(birthdateContainer);
    birthdateContainer.click();

    wait.forElementClickable(birthMonthField);
    birthMonthField.sendKeys(Integer.toString(month));

    waitAndClick(birthDayField);
    birthDayField.sendKeys(Integer.toString(day));

    waitAndClick(birthYearField);
    birthYearField.sendKeys(Integer.toString(year));
  }

  public void clickRegister() {
    wait.forElementVisible(registerButton);
    registerButton.click();
    Log.log("clickRegister", "Create account button clicked", true);
    waitForElementNotVisibleByElement(registerButton);
  }
}
