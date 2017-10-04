package com.wikia.webdriver.elements.mercury.components.discussions.common;

import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.pageobjectsfactory.pageobject.BasePageObject;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;

public abstract class BaseReplyCreator extends BasePageObject implements ReplyCreator {

  @Getter
  private By errorNotification = By.className("error");

  protected abstract WebElement getReplyCreatorTextArea();
  protected abstract WebElement getEditor();
  protected abstract WebElement getDialogSignIn();
  protected abstract WebElement getOkButtonInSignInDialog();
  protected abstract WebElement getSignInButtonInSignInDialog();
  protected abstract WebElement getGuidelinesReadButton();
  protected abstract WebElement getTextarea();
  protected abstract WebElement getSubmitButton();
  protected abstract WebElement getLoadingSuccess();
  protected abstract WebElement getImagePreview();
  protected abstract WebElement getUploadButton();
  protected abstract WebElement getImageDeleteButton();
  protected abstract By getOpenGraphContainer();
  protected abstract By getOpenGraphText();

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
    return getEditor().findElement(getErrorNotification()).getText();
  }

  public BaseReplyCreator removeImage() {
    waitAndClick(getImageDeleteButton());
    wait.forElementNotVisible(getImagePreview());
    return this;
  }

  public boolean hasOpenGraph() {
    boolean result = false;
    final WebElement openGraphContainer = getEditor()
      .findElement(getOpenGraphContainer());
    if (null != openGraphContainer) {
      result = null != openGraphContainer.findElement(getOpenGraphText());
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
