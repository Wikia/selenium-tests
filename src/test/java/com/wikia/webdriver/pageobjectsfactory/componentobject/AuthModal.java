package com.wikia.webdriver.pageobjectsfactory.componentobject;

import com.wikia.webdriver.common.core.WikiaWebDriver;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuthModal extends WikiBasePageObject {

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
  @FindBy(css = ".forgotten-password")
  private WebElement forgottenPasswordLink;

  private WebDriver webDriver;
  private String mainWindowHandle;

  public AuthModal(WikiaWebDriver webDriver){
    super();
    this.webDriver = webDriver;
    waitForNewWindow();
    this.mainWindowHandle =  this.webDriver.getWindowHandle();
    for(String winHandle : this.webDriver.getWindowHandles()){
      this.webDriver.switchTo().window(winHandle);
    }
    PageFactory.initElements(webDriver, this);
  }

  public boolean isOpened(){
    boolean isOpenedResult = authModal.isDisplayed();
    return isOpenedResult;
  }

  public void login(String username, String password){
    usernameField.sendKeys(username);
    passwordField.sendKeys(password);
    signInButton.click();
    this.webDriver.switchTo().window(this.mainWindowHandle);
  }

  public void login(User user){
    login(user.getUserName(), user.getPassword());
  }

  public void clickForgotPasswordLink(){
    forgottenPasswordLink.click();
  }

}
