package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.WebElement;

public abstract class BaseReplyCreator extends BasePageObject implements ReplyCreator {

  protected abstract WebElement getReplyCreator();
  protected abstract WebElement getDialogSignIn();
  protected abstract WebElement getOkButtonInSignInDialog();
  protected abstract WebElement getSignInButtonInSignInDialog();
  protected abstract WebElement getGuidelinesReadButton();
  protected abstract WebElement getTextarea();
  protected abstract WebElement getSubmitButton();
  protected abstract WebElement getLoadingSuccess();
  protected abstract WebElement getAlertNotification();

  @Override
  public ReplyCreator click() {
    wait.forElementVisible(getReplyCreator()).click();
    return this;
  }

  @Override
  public boolean isModalDialogVisible() {
    return wait.forElementVisible(getDialogSignIn()).isDisplayed();
  }

  @Override
  public ReplyCreator clickOkButtonInSignInDialog() {
    getOkButtonInSignInDialog().click();
    return this;
  }


  @Override
  public ReplyCreator clickSignInButtonInSignInDialog() {
    getSignInButtonInSignInDialog().click();
    return this;
  }

  @Override
  public ReplyCreator clickGuidelinesReadButton() {
    getGuidelinesReadButton().click();
    return this;
  }

  @Override
  public ReplyCreator clearText() {
    wait.forElementVisible(getTextarea()).clear();
    return this;
  }

  @Override
  public ReplyCreator add(final String text) {
    wait.forElementVisible(getTextarea()).sendKeys(text);
    return this;
  }

  @Override
  public boolean isSubmitButtonActive() {
    return getSubmitButton().isEnabled();
  }

  @Override
  public ReplyCreator clickSubmitButton() {
    getSubmitButton().click();
    return this.waitForConfirmation();
  }

  private ReplyCreator waitForConfirmation() {
    waitSafely(() -> wait.forElementVisible(getLoadingSuccess()));
    waitSafely(() -> wait.forElementNotVisible(getLoadingSuccess()));
    return this;
  }

  public ReplyCreator uploadValidImage() {
    getUploadButton().sendKeys(ContentLoader.getImage());
    wait.forElementVisible(getImagePreview());
    return this;
  }

  public String uploadUnsupportedImage() {
    getUploadButton().sendKeys(ContentLoader.getUnsupportedImage());
    wait.forElementVisible(getAlertNotification());
    return getAlertNotification().getText();
  }

  protected abstract WebElement getImagePreview();

  protected abstract WebElement getUploadButton();
}
