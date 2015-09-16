package com.wikia.webdriver.pageobjectsfactory.componentobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by qaga on 2015-09-16.
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

  private WebDriver webDriver;

  public NewAuthModal(WebDriver webdriver){
    this.webDriver = webdriver;

    PageFactory.initElements(webdriver, this);
  }

  private void switchToFrame(){
    webDriver.switchTo().frame(webDriver.findElement(By.cssSelector(".auth-modal iframe")));
  }

  private void switchBack(){
    webDriver.switchTo().defaultContent();
  }

  public boolean isOpened(){
    switchToFrame();
    newAuthModal.isDisplayed();
    switchBack();
    return true;
  }

  public void login(String username, String password){
    switchToFrame();
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    signInButton.click();
    switchBack();
  }
}
