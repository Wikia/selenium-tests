package com.wikia.webdriver.elements.mercury.components.loginAndSignup;


import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.url.UrlBuilder;
import com.wikia.webdriver.elements.mercury.components.TopBar;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterArea {

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
  @FindBy(css = ".loginAndSignup")
  private WebElement newloginButton;
  @FindBy(css = ".signup-provider-email")
  private WebElement signupButton;

  private WebDriver driver;
  private Wait wait;
  private UrlBuilder urlBuilder;


  public RegisterArea(WebDriver driver) {
    this.driver = driver;
    this.wait = new Wait(driver);

    PageFactory.initElements(driver, this);
  }

  public RegisterArea typeEmailAddress(String email) {
    wait.forElementVisible(signupEmail);
    signupEmail.sendKeys(email);
    return this;
  }

  public RegisterArea typeUsername(String username) {
    wait.forElementVisible(signupUsername);
    signupUsername.sendKeys(username);
    return this;
  }

  public RegisterArea typePassword(String password) {
    wait.forElementVisible(signupPassword);
    signupPassword.sendKeys(password);
    return this;
  }

  public RegisterArea typeBirthdate(String month, String day, String year) {
    wait.forElementVisible(signupBirthdate);
    signupBirthdate.click();

    wait.forElementVisible(signupBirthMonth);
    signupBirthMonth.click();
    signupBirthMonth.sendKeys(month);

    wait.forElementVisible(signupBirthDay);
    signupBirthDay.click();
    signupBirthDay.sendKeys(day);

    wait.forElementVisible(signupBirthYear);
    signupBirthYear.click();
    signupBirthYear.sendKeys(year);

    return this;
  }

  public RegisterArea register(String user, String password, String email, DateTime birthday) {
        typeEmailAddress(email).
        typeUsername(user).
        typePassword(password).
        typeBirthdate(String.valueOf(birthday.getMonthOfYear()),
                      String.valueOf(birthday.getDayOfMonth()),
                      String.valueOf(birthday.getYear())).
        clickSignUpSubmitButton();

    return this;
  }

  public void clickSignUpSubmitButton() {
    wait.forElementVisible(signupSubmitButton);
    signupSubmitButton.click();
  }

  public void verifyAvatarAfterSignup() {
    new TopBar(driver).openNavigation();
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
    driver.get(urlBuilder.getUrlForWiki(Configuration.getWikiName()) + "clickSignUpSubmitButton");
  }




}
