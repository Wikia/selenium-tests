package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ImageReview extends WikiBasePageObject {

  @FindBy(css = ".sub-head")
  private WebElement navbar;

  @FindBy(css = ".sub-head .sub-head--title")
  private WebElement title;

  @FindBy(css = ".image-review-action-buttons")
  private WebElement actionButtonWrapper;

  @FindBy(css = ".alert-notifications")
  private WebElement alertNotification;

  @FindBy(css = ".image-review-text")
  private WebElement topMessage;

  @FindBy(css = ".image-review-text > p:nth-child(2) > strong")
  private WebElement currentStatusMessage;

  @FindBy(css = ".image-review-modal-history")
  private WebElement imageInfoInModal;

  @FindBy(css = ".image-review-image-details > img")
  private WebElement imageInModal;

  @FindBy(css = ".image-review-image-details > p:nth-child(1) > a")
  private WebElement contextLinkInModal;

  @FindBy(css = ".image-review-modal-history")
  private WebElement imageHistoryInModal;

  @FindBy(css = ".image-review-button-group > .button-link > a")
  private WebElement contextLinkButton;

  @FindBy(css = ".sub-head > button:nth-child(2)")
  private WebElement showSummaryButton;

  @FindBy(css = ".sub-head > .sub-head--cancel")
  private WebElement backButton;

  @FindBy(css = ".image-review-summary-dialog")
  private WebElement summaryDialog;

  @FindBy(css = ".sub-head > button")
  private List<WebElement> navbarButtons;

  @FindBy(css = ".image-review-action-buttons > button")
  private List<WebElement> actionButtons;

  @FindBy(css = ".image-review-image-container")
  private List<WebElement> imageContainer;

  @FindBy(css = ".image-review-image-container > img")
  private List<WebElement> imagesToReview;

  @FindBy(css = ".image-review-image-container > .image-review-button-group > button:nth-child(1)")
  private List<WebElement> acceptImageButtons;

  @FindBy(css = ".image-review-image-container > .image-review-button-group > button:nth-child(2)")
  private List<WebElement> rejectImageButtons;

  @FindBy(css = ".image-review-image-container > .image-review-button-group > button:nth-child(3)")
  private List<WebElement> questionableImageButtons;


  public ImageReview open() {
    getUrl("http://www.wikia.com/image-review");

    return this;
  }

  public boolean isTitleVisible() {
    try {
      return title.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isAlertNotificationVisible() {
    try {
      return alertNotification.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isTopMessageVisible() {
    try {
      return topMessage.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isImageInfoInModalVisible() {
    try {
      return imageInfoInModal.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isImageInModalVisible() {
    try {
      return imageInModal.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isContextLinkInModalVisible() {
    try {
      return contextLinkInModal.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isImageHistoryInModalVisible() {
    try {
      return imageHistoryInModal.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isContextLinkButtonInModalVisible() {
    try {
      return contextLinkButton.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isBackButtonVisible() {
    try {
      return backButton.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public boolean isSummaryDialogVisible() {
    try {
      return summaryDialog.isDisplayed();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo(e.getMessage());

      return false;
    }
  }

  public String getAlertNotificationText() {
    wait.forElementVisible(alertNotification);

    return alertNotification.getText();
  }

  public String getCurrentStatusText() {
    wait.forElementVisible(topMessage);
    wait.forElementVisible(currentStatusMessage);

    return currentStatusMessage.getText();
  }

  public int getNavbarButtonsNumber() {
    wait.forElementVisible(navbar);

    return navbarButtons.size();
  }

  public int getActionButtonsNumber() {
    wait.forElementVisible(actionButtonWrapper);

    return actionButtons.size();
  }

  public int getImagesToReviewNumber() {
    wait.forElementVisible(imagesToReview.get(0));

    return imagesToReview.size();
  }

  public boolean areNavbarButtonsClickable() {
    wait.forElementVisible(navbar);

    for (WebElement navbarButton : navbarButtons) {
      wait.forElementClickable(navbarButton);
    }

    return true;
  }

  public boolean areButtonsForImageClickable() {
    for (int i=0; i<imagesToReview.size(); i++) {
      wait.forElementClickable(acceptImageButtons.get(i));
      wait.forElementClickable(rejectImageButtons.get(i));
      wait.forElementClickable(questionableImageButtons.get(i));
    }

    return true;
  }

  public ImageReview clickImage(int index) {
    wait.forElementClickable(imagesToReview.get(index));
    imagesToReview.get(index).click();

    return this;
  }

  public ImageReview clickShowSummaryButton() {
    wait.forElementClickable(showSummaryButton);
    showSummaryButton.click();

    return new ImageReview();
  }

  public ImageReview clickBackButton() {
    wait.forElementClickable(backButton);
    backButton.click();

    return new ImageReview();
  }

}
