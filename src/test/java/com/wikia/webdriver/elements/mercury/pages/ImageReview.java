package com.wikia.webdriver.elements.mercury.pages;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.TimeoutException;
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

  @FindBy(css = ".image-review-button-group > .button-link > a")
  private WebElement contextLinkButton;

  @FindBy(css = ".sub-head > button:nth-child(2)")
  private WebElement showSummaryButton;

  @FindBy(css = ".sub-head > .sub-head--cancel")
  private WebElement backButton;

  @FindBy(css = ".image-review-summary-dialog")
  private WebElement summaryDialog;

  @FindBy(css = ".image-review-summary-dialog > .image-review-toggle-summary-button")
  private WebElement showStatisticsButton;

  @FindBy(css = ".image-review-summary-dialog > a")
  private WebElement downloadCsvButton;

  @FindBy(css = ".sub-head > button")
  private List<WebElement> navbarButtons;

  @FindBy(css = ".image-review-summary-input")
  private WebElement showHistoryInput;

  @FindBy(css = ".image-review-summary-dialog:nth-of-type(2) > button")
  private WebElement showHistoryButton;

  @FindBy(css = ".image-review-action-buttons > button")
  private List<WebElement> actionButtons;

  @FindBy(css = ".image-review-summary-dialog > .image-review-date-input > input")
  private List<WebElement> statisticsDateInputs;


  public ImageReview open() {
    getUrl(String.format("%s/image-review", urlBuilder.getWikiGlobalURL()));
    return this;
  }

  public boolean isTitleDisplayed() {
    return this.isElementDisplayed(title);
  }

  public boolean isAlertNotificationDisplayed() {
    return this.isElementDisplayed(alertNotification);
  }

  public boolean isTopMessageDisplayed() {
    return this.isElementDisplayed(topMessage);
  }

  public boolean isContextLinkButtonInModalDisplayed() {
    return this.isElementDisplayed(contextLinkButton);
  }

  public boolean isBackButtonDisplayed() {
    return this.isElementDisplayed(backButton);
  }

  public boolean isSummaryDialogDisplayed() {
    return this.isElementDisplayed(summaryDialog);
  }

  public boolean isShowStatisticsButtonDisplayed() {
    return this.isElementDisplayed(showStatisticsButton);
  }

  public boolean isStatisticsDateInputDisplayed(int index) {
    return this.isElementDisplayed(statisticsDateInputs.get(index));
  }

  public boolean isDownloadCsvDisplayed() {
    return this.isElementDisplayed(downloadCsvButton);
  }

  public boolean isShowHistoryInputDisplayed() {
    return this.isElementDisplayed(showHistoryInput);
  }

  public boolean isShowHistoryButtonDisplayed() {
    return this.isElementDisplayed(showHistoryButton);
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

  public boolean areNavbarButtonsClickable() {
    wait.forElementVisible(navbar);

    for (WebElement navbarButton : navbarButtons) {
      try {
        wait.forElementClickable(navbarButton);
      } catch(TimeoutException e) {
        PageObjectLogging.logInfo(e.getMessage());

        return false;
      }
    }

    return true;
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
