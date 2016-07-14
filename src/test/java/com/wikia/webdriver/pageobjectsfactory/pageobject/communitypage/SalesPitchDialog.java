package com.wikia.webdriver.pageobjectsfactory.pageobject.communitypage;

import com.wikia.webdriver.common.logging.PageObjectLogging;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesPitchDialog extends WikiBasePageObject {

  private By salesPitchDialog = By.cssSelector("#CommunityPageBenefitsModal");

  @FindBy(css = "#CommunityPageBenefitsModal .community-page-entry-point-button")
  private WebElement helpOutButton;

  @FindBy(css = ".community-page-benefits-image")
  private WebElement image;

  @FindBy(css = ".community-page-benefits-content")
  private WebElement dialogContent;

  public boolean isDialogVisible() {
    wait.forElementVisible(salesPitchDialog);

    return true;
  }

  public boolean isDialogNotVisible() {
    wait.forElementNotVisible(salesPitchDialog);

    return true;
  }

  public SpecialCommunity clickHelpOutButton() {
    try {
      wait.forElementVisible(helpOutButton);
      helpOutButton.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo("Help out button is not visible");
    }

    return new SpecialCommunity();
  }

  public SpecialCommunity clickDialogImage() {
    try {
      wait.forElementVisible(image);
      image.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo("Dialog image is not visible");
    }

    return new SpecialCommunity();
  }

  public SpecialCommunity clickDialogContent() {
    try {
      wait.forElementVisible(dialogContent);
      dialogContent.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo("Dialog content is not visible");
    }

    return new SpecialCommunity();
  }

}
