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

  public boolean isVisible() {
    wait.forElementVisible(salesPitchDialog);

    return true;
  }

  public boolean isNotVisible() {
    wait.forElementNotVisible(salesPitchDialog);

    return true;
  }

  public SpecialCommunity clickHelpOutButton() {
    try {
      wait.forElementVisible(helpOutButton);
      helpOutButton.click();
    } catch (NoSuchElementException e) {
      PageObjectLogging.logInfo("Entry point button is not visible");
    }

    return new SpecialCommunity();
  }

}
