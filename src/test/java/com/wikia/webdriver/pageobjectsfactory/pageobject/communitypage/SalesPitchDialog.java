package com.wikia.webdriver.pageobjectsfactory.pageobject.communitypage;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesPitchDialog extends WikiBasePageObject {

  @FindBy(css = "#CommunityPageBenefitsModal")
  private WebElement salesPitchDialog;

  @FindBy(css = "#CommunityPageBenefitsModal .community-page-entry-point-button")
  private WebElement helpOutButton;

  @FindBy(css = ".community-page-benefits-image")
  private WebElement image;

  @FindBy(css = ".community-page-benefits-content")
  private WebElement dialogContent;

  public boolean isDialogVisible() {
    try {
      wait.forElementVisible(salesPitchDialog, 5);
      return true;
    } catch (NoSuchElementException e) {
      PageObjectLogging.log("Dialog is not visible", e, true);
      return false;
    } catch (TimeoutException e) {
      PageObjectLogging.log(e.getMessage(), e, true);
      return false;
    }
  }

  public SpecialCommunity clickHelpOutButton() {
    try {
      wait.forElementVisible(helpOutButton);
      helpOutButton.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.log("Button is not visible", e, true);
    }

    return new SpecialCommunity();
  }

  public SpecialCommunity clickDialogImage() {
    try {
      wait.forElementVisible(image);
      image.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.log("Dialog image is not visible", e, true);
    }

    return new SpecialCommunity();
  }

  public SpecialCommunity clickDialogContent() {
    try {
      wait.forElementVisible(dialogContent);
      dialogContent.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.log("Dialog content is not visible", e, true);
    }

    return new SpecialCommunity();
  }

}
