package com.wikia.webdriver.elements.mercury.components.discussions.desktop;

import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class PostsCreatorDesktop extends BasePageObject {

  @FindBy (css = ".modal-dialog")
  private WebElement postCreator;

  @FindBy (css = "#ember1084 .modal-dialog")
  private WebElement dialogSignIn;

  @FindBy (css = ".modal-dialog-content + footer > :first-child")
  private WebElement okButtonInSignInDialog;

  @FindBy (css = ".modal-dialog-content + footer > :last-child")
  private WebElement signInButtonInSignInDialog;

  public PostsCreatorDesktop clickPostCreator() {
    postCreator.click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return dialogSignIn.isDisplayed();
  }

  public PostsCreatorDesktop clickOkButtonInSignInDialog() {
    okButtonInSignInDialog.click();
    return this;
  }

  public PostsCreatorDesktop clickSignInButtonInSignInDialog() {
    signInButtonInSignInDialog.click();
    return this;
  }





}
