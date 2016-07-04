package com.wikia.webdriver.pageobjectsfactory.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleLoginPopup extends BasePageObject{

  @FindBy(css = "#Email")
  private WebElement usernameInput;

  @FindBy(css = "#Passwd")
  private WebElement passwordInput;

  @FindBy(css = "#signIn")
  private WebElement signInButton;

  @FindBy(css = "#next")
  private WebElement nextButton;

  public GoogleLoginPopup(WebDriver driver) {
    super();
  }

  public void SignInToGoogle(String userName, String password){
    wait.forElementVisible(usernameInput);
    usernameInput.sendKeys(userName);
    nextButton.click();
    wait.forElementVisible(passwordInput);
    passwordInput.sendKeys(password);
    wait.forElementVisible(signInButton);
    signInButton.click();
  }
}
