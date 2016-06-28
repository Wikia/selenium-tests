package com.wikia.webdriver.pageobjectsfactory.pageobject.communitypage;

import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SalesPitchDialog extends WikiBasePageObject {

  private By salesPitchDialog = By.cssSelector("#CommunityPageBenefitsModal");

  @FindBy(css = "#CommunityPageBenefitsModal .community-page-entry-point-button")
  private WebElement entryPointButton;

  public boolean isVisible() {
    wait.forElementVisible(salesPitchDialog);

    return true;
  }

  public boolean isNotVisible() {
    wait.forElementNotVisible(salesPitchDialog);

    return true;
  }

  public boolean clickEntryPointButton() {
    isVisible();

    wait.forElementVisible(entryPointButton);
    entryPointButton.click();

    return true;
  }

}
