package com.wikia.webdriver.elements.mercury.old;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.elements.mercury.components.Navigation;
import com.wikia.webdriver.elements.mercury.components.TopBar;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPageObject {

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
  private WebElement signupSubmitButton;
  @FindBy(css = ".wikia-nav__avatar")
  private WebElement avatar;
  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[1]/small")
  private WebElement emailError;
  @FindBy(css = "#signupForm div:nth-child(2) small")
  private WebElement usernameError;
  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[3]/small")
  private WebElement passwordError;
  @FindBy(css = "#signupForm > small.error")
  private WebElement genericError;
  @FindBy(css = " header.auth-header")
  private WebElement registerHeader;
  @FindBy(css = ".login")
  private WebElement newloginButton;
  @FindBy(css = ".signup-provider-email")
  private WebElement signupButton;

  private WebDriver driver;
  private Wait wait;
  private UrlBuilder urlBuilder;

  public SignupPageObject(WebDriver driver) {
    this.driver = driver;
    this.urlBuilder = new UrlBuilder();
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  private SignupPageObject typeEmailAddress(String email) {
    setValue(signupEmail, email);
    return this;
  }

  private SignupPageObject typeUsername(String username) {
    setValue(signupUsername, username);
    return this;
  }

  private SignupPageObject typePassword(String password) {
    setValue(signupPassword, password);
    return this;
  }

  private void setValue(WebElement input, String value) {
    wait.forElementVisible(input).click();
    input.clear();
    input.sendKeys(value);
    PageObjectLogging.log("Set value",
      String.format("value of input identified by: %s set to %s",
        input.getCssValue("class"), value), true);
  }

  private SignupPageObject typeBirthdate(String month, String day, String year) {
    wait.forElementVisible(signupBirthdate);
    signupBirthdate.click();

    setValue(signupBirthMonth, month);
    setValue(signupBirthDay, day);
    setValue(signupBirthYear, year);

    return this;
  }

  public void register() {
    wait.forElementVisible(signupSubmitButton);
    signupSubmitButton.click();
  }

  public void verifyAvatarAfterSignup() {
    new TopBar().openNavigation();
    wait.forElementVisible(avatar);
    Assertion.assertTrue(avatar.isDisplayed());
  }

  public void verifyUsernameTakenError() {
    wait.forElementVisible(usernameError);
    Assertion.assertEquals(usernameError.getText(), "Username is taken");
  }

  public void verifyPasswordError() {
    wait.forElementVisible(passwordError);
    Assertion.assertEquals(passwordError.getText(), "Password and username cannot match");
  }

  public void verifyBirthdateError() {
    wait.forElementVisible(genericError);
    Assertion.assertEquals(genericError.getText(),
                           "We cannot complete your registration at this time");
  }

  public String getRegisterHeaderText() {
    wait.forElementVisible(registerHeader);
    return registerHeader.getText();
  }

  public void openRegisterPage() {
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "/register");
  }

  public SignupPageObject openMobileSignupPage() {
    new TopBar().openNavigation();
    new Navigation(driver).clickOnSignInRegisterButton();
    wait.forElementVisible(signupButton);
    signupButton.click();
    return new SignupPageObject(driver);
  }

  public SignupPageObject signUp(String user, String password, String email, DateTime birthday) {
    typeEmailAddress(email).
        typeUsername(user).
        typePassword(password).
        typeBirthdate(String.valueOf(birthday.getMonthOfYear()),
                      String.valueOf(birthday.getDayOfMonth()),
                      String.valueOf(birthday.getYear())).
        register();

    return this;
  }
}
