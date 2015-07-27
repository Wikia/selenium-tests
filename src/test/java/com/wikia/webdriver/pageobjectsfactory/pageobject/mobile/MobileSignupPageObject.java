package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


/**
 * Created by rcunningham on 6/30/15.
 */
public class MobileSignupPageObject extends MobileBasePageObject {

  public MobileSignupPageObject(WebDriver driver) {
    super(driver);
  }

  @FindBy(css = "#signupEmail")
  private WebElement signupEmail;
  @FindBy(css = "#signupUsername")
  private WebElement signupUsername;
  @FindBy(css = "#signupPassword")
  private WebElement signupPassword;
  @FindBy(css = "#signupBirthDate")
  private WebElement signupBirthdate;
  @FindBy(css = ".birth-month")
  private WebElement signupBirthMonth;
  @FindBy(css = ".birth-day")
  private WebElement signupBirthDay;
  @FindBy(css = ".birth-year")
  private WebElement signupBirthYear;
  @FindBy(css = "#signupSubmit")
  private WebElement signupButton;
  @FindBy(css = ".avatar")
  private WebElement avatar;
  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[1]/small")
  private WebElement emailError;
  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[2]/small[2]")
  private WebElement usernameError;
  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[3]/small")
  private WebElement passwordError;
  @FindBy(css = "#signupForm > small.error")
  private WebElement genericError;
  @FindBy(css = " header.auth-header")
  private WebElement registerHeader;

  public MobileSignupPageObject typeEmailAddress(String email) {
    waitForElementByElement(signupEmail);
    signupEmail.sendKeys(email);
    return this;
  }

  public MobileSignupPageObject typeUsername(String username) {
    waitForElementByElement(signupUsername);
    signupUsername.sendKeys(username);
    return this;
  }

  public MobileSignupPageObject typePassword(String password) {
    waitForElementByElement(signupPassword);
    signupPassword.sendKeys(password);
    return this;
  }

  public MobileSignupPageObject typeBirthdate(String month, String day, String year) {
    waitForElementByElement(signupBirthdate);
    scrollAndClick((signupBirthdate));

    waitForElementByElement(signupBirthMonth);
    scrollAndClick((signupBirthMonth));
    signupBirthMonth.sendKeys(month);

    waitForElementByElement(signupBirthDay);
    signupBirthDay.click();
    signupBirthDay.sendKeys(day);

    waitForElementByElement(signupBirthYear);
    signupBirthYear.click();
    signupBirthYear.sendKeys(year);

    return this;
  }

  public void register() {
    waitForElementByElement(signupButton);
    scrollAndClick(signupButton);
  }

  public void verifyAvatarAfterSignup() {
    waitForElementByElement(avatar);
    Assertion.assertTrue(avatar.isDisplayed());
  }

  public void verifyEmailInUseError() {
    waitForElementByElement(emailError);
    Assertion.assertEquals(emailError.getText(), "Email is already registered on Wikia");
  }

  public void verifyUsernameTakenError() {
    waitForElementByElement(usernameError);
    Assertion.assertEquals(usernameError.getText(), "Someone already has this username. Try a different one!");
  }

  public void verifyPasswordError() {
    waitForElementByElement(passwordError);
    Assertion.assertEquals(passwordError.getText(), "Your password must be different from your username.");
  }

  public void verifyBirthdateError() {
    waitForElementByElement(genericError);
    Assertion.assertEquals(genericError.getText(), "We can not complete your registration at this time.");
  }

  public String getHeadertext() {
    return registerHeader.getText();
  }
  public void openRegisterPage() {
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "register");
  }

}
