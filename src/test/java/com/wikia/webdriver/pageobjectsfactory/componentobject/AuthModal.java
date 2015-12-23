package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.helpers.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthModal {

  @FindBy(css = ".auth.desktop.signin-page")
  private WebElement authModal;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;
  @FindBy(css = "#loginPassword")
  private WebElement passwordField;
  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;
  @FindBy(css = ".auth-modal iframe")
  private WebElement iFrame;

  private WebDriver webDriver;

  public AuthModal(WebDriver webDriver){
    this.webDriver = webDriver;

    PageFactory.initElements(webDriver, this);
  }

  private void switchToFrame(){
    webDriver.switchTo().frame(iFrame);
  }

  private void switchBack(){
    webDriver.switchTo().defaultContent();
  }

  public boolean isOpened(){
    switchToFrame();
    boolean isOpenedResult = authModal.isDisplayed();
    switchBack();
    return isOpenedResult;
  }

  public void login(String username, String password){
    new Wait(webDriver).forElementVisible(iFrame);
    switchToFrame();
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    signInButton.click();
    switchBack();
  }

  public void login(User user){
    login(user.getUserName(), user.getPassword());
  }
}
