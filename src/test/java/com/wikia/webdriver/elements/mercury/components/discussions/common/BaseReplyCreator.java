package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;

public abstract class BaseReplyCreator extends BasePageObject implements ReplyCreator {

  protected abstract WebElement getReplyCreatorTextArea();
  protected abstract WebElement getReplyCreatorWrapper();
  protected abstract WebElement getDialogSignIn();
  protected abstract WebElement getOkButtonInSignInDialog();
  protected abstract WebElement getSignInButtonInSignInDialog();
  protected abstract WebElement getGuidelinesReadButton();
  protected abstract WebElement getTextarea();
  protected abstract WebElement getSubmitButton();
  protected abstract WebElement getLoadingSuccess();
  protected abstract WebElement getAlertNotification();
  protected abstract WebElement getImagePreview();
  protected abstract WebElement getUploadButton();
  protected abstract WebElement getImageDeleteButton();

  @Override
  public ReplyCreator click() {
    wait.forElementVisible(getReplyCreatorTextArea()).click();
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

  public BaseReplyCreator uploadImage() {
    getUploadButton().sendKeys(ContentLoader.getImage());
    wait.forElementVisible(getImagePreview());
    return this;
  }

  public String uploadUnsupportedImage() {
    getUploadButton().sendKeys(ContentLoader.getUnsupportedImage());
    wait.forElementVisible(getAlertNotification());
    return getAlertNotification().getText();
  }

  public BaseReplyCreator removeImage() {
    waitAndClick(getImageDeleteButton());
    wait.forElementNotVisible(getImagePreview());
    return this;
  }

  public boolean hasOpenGraph() {
    boolean result = false;
    final WebElement openGraphContainer = getReplyCreatorWrapper()
      .findElement(By.className("og-container"));
    if (null != openGraphContainer) {
      result = null != openGraphContainer.findElement(By.className("og-texts"));
    }
    return result;
  }

  public BaseReplyCreator startReplyCreation() {
    return startReplyCreationWith(TextGenerator.defaultText());
  }

  @Override
  public BaseReplyCreator startReplyCreationWith(String description) {
    click().clickGuidelinesReadButton().add(description);
    return this;
  }

  public BaseReplyCreator startReplyCreationWithLink(URL link) {
    return startReplyCreationWith(String.format(" %s ", link.toString()));
  }

}
