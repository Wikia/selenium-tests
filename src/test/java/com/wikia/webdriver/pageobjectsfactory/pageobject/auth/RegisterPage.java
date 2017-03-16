package com.wikia.webdriver.pageobjectsfactory.pageobject.auth;

import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.configuration.Configuration;
import com.wikia.webdriver.elements.mercury.components.TopBar;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BaseAuthPage {

  public RegisterPage open() {
      driver.get(urlBuilder.getUrlForWiki() + URLsContent.SPECIAL_USER_SIGNUP);
      return this;
  }

  @FindBy(css = ".auth.desktop.signin-page")
  private WebElement authModal;
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
  @FindBy(css = "#signupForm div:nth-child(2) small")
  private WebElement usernameError;
  @FindBy(xpath = "//*[@id=\"signupForm\"]/div[3]/small")
  private WebElement passwordError;
  @FindBy(css = "#signupForm > small.error")
  private WebElement genericError;
  @FindBy(css = " header.auth-header")
  private WebElement registerHeader;

  private final String mainWindowHandle;

  public RegisterPage() {
    this(false);
  }

  public RegisterPage(boolean waitForNewWindow) {
    super();
    if (waitForNewWindow) {
      waitForNewWindow();
      this.mainWindowHandle = driver.getWindowHandle();
    } else {
      this.mainWindowHandle = null;
    }
  }

  public void switchToAuthModalHandle() {
    for (String winHandle : driver.getWindowHandles()) {
      driver.switchTo().window(winHandle);
    }
  }

  public void switchToMainWindowHandle() {
    driver.switchTo().window(this.mainWindowHandle);
  }

  public boolean isModalOpen() {
    switchToAuthModalHandle();
    boolean isOpenedResult = authModal.isDisplayed();
    switchToMainWindowHandle();
    return isOpenedResult;
  }

  public RegisterPage typeEmailAddress(String email) {
    wait.forElementVisible(signupEmail);
    signupEmail.sendKeys(email);
    return this;
  }

  public RegisterPage typeUsername(String username) {
    wait.forElementVisible(signupUsername);
    signupUsername.sendKeys(username);
    return this;
  }

  public RegisterPage typePassword(String password) {
    wait.forElementVisible(signupPassword);
    signupPassword.sendKeys(password);
    return this;
  }

  public RegisterPage typeBirthdate(String month, String day, String year) {
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

  public void clickSignUpSubmitButton() {
    wait.forElementVisible(signupSubmitButton);
    signupSubmitButton.click();
  }

  public void verifyAvatarAfterSignup() {
    new TopBar(driver).openNavigation();
    wait.forElementVisible(avatar);
    Assertion.assertTrue(avatar.isDisplayed());
  }

  public boolean doesErrorMessageContainText() {
    return usernameError.getText().contains("Username is taken");
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


