package com.wikia.webdriver.elements.mercury.components.discussions.common.contribution;

import com.wikia.webdriver.common.core.helpers.ContentLoader;
import com.wikia.webdriver.elements.mercury.components.discussions.common.TextGenerator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.URL;

public abstract class BaseReplyCreator extends ContributionEditor implements Editor {



  @Override
  public BaseReplyCreator click() {
    wait.forElementVisible(getTextArea()).click();
    return this;
  }

  public boolean isModalDialogVisible() {
    return wait.forElementVisible(getSignInDialog()).isDisplayed();
  }

  @Override
  public BaseReplyCreator clickOkButtonInSignInDialog() {
    getOkButtonInSignInDialog().click();
    return this;
  }


  @Override
  public BaseReplyCreator clickSignInButtonInSignInDialog() {
    getSignInButtonInSignInDialog().click();
    return this;
  }

  @Override
  public BaseReplyCreator clearText() {
    wait.forElementVisible(getTextArea()).clear();
    return this;
  }

  @Override
  public BaseReplyCreator addTextWith(final String text) {
    wait.forElementVisible(getTextArea()).sendKeys(text);
    return this;
  }

  @Override
  public boolean isSubmitButtonActive() {
    return getSubmitButton().isEnabled();
  }

  private BaseReplyCreator waitForConfirmation() {
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
    wait.forElementVisible(getErrorNotification()).getText();
    return wait.forElementVisible(getErrorNotification()).getText();
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

  public BaseReplyCreator startReplyCreationWith(String description) {
    click().closeGuidelinesMessage().addTextWith(description);
    return this;
  }

  public BaseReplyCreator startReplyCreationWithLink(URL link) {
    return startReplyCreationWith(String.format(" %s ", link.toString()));
  }

}
