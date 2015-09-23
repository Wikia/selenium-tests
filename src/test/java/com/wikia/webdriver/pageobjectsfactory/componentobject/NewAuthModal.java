package com.wikia.webdriver.pageobjectsfactory.componentobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @ownership Social
 */
public class NewAuthModal {

  @FindBy(css = ".auth.desktop.signin-page")
  private WebElement newAuthModal;
  @FindBy(css = "#loginUsername")
  private WebElement usernameField;
  @FindBy(css = "#loginPassword")
  private WebElement passwordField;
  @FindBy(css = "#loginSubmit")
  private WebElement signInButton;
  @FindBy(css = ".auth-modal iframe")
  private WebElement iFrame;

  private WebDriver webDriver;

  public NewAuthModal(WebDriver webDriver){
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
    boolean isOpenedResult = newAuthModal.isDisplayed();
    switchBack();
    return isOpenedResult;
  }

  public void login(String username, String password){
    switchToFrame();
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    signInButton.click();
    switchBack();
  }
}
