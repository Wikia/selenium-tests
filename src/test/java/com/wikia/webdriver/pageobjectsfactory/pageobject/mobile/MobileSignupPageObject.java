package com.wikia.webdriver.pageobjectsfactory.pageobject.mobile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.wikia.webdriver.common.core.Assertion;


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

  public MobileSignupPageObject typeEmailAddress(String email) {
    wait.forElementVisible(signupEmail);
    signupEmail.sendKeys(email);
    return this;
  }

  public MobileSignupPageObject typeUsername(String username) {
    wait.forElementVisible(signupUsername);
    signupUsername.sendKeys(username);
    return this;
  }

  public MobileSignupPageObject typePassword(String password) {
    wait.forElementVisible(signupPassword);
    signupPassword.sendKeys(password);
    return this;
  }

  public MobileSignupPageObject typeBirthdate(String month, String day, String year) {
    wait.forElementVisible(signupBirthdate);
    scrollAndClick((signupBirthdate));

    wait.forElementVisible(signupBirthMonth);
    scrollAndClick((signupBirthMonth));
    signupBirthMonth.sendKeys(month);

    wait.forElementVisible(signupBirthDay);
    signupBirthDay.click();
    signupBirthDay.sendKeys(day);

    wait.forElementVisible(signupBirthYear);
    signupBirthYear.click();
    signupBirthYear.sendKeys(year);

    return this;
  }

  public void register() {
    wait.forElementVisible(signupButton);
    scrollAndClick(signupButton);
  }

  public void verifyAvatarAfterSignup() {
    wait.forElementVisible(avatar);
    Assertion.assertTrue(avatar.isDisplayed());
  }

  public void verifyEmailInUseError() {
    wait.forElementVisible(emailError);
    Assertion.assertEquals(emailError.getText(), "Email is already registered on Wikia");
  }

  public void verifyUsernameTakenError() {
    wait.forElementVisible(usernameError);
    Assertion.assertEquals(usernameError.getText(), "Someone already has this username. Try a different one!");
  }

  public void verifyPasswordError() {
    wait.forElementVisible(passwordError);
    Assertion.assertEquals(passwordError.getText(), "Your password must be different from your username.");
  }

  public void verifyBirthdateError() {
    wait.forElementVisible(genericError);
    Assertion.assertEquals(genericError.getText(), "We can not complete your registration at this time.");
  }
}
