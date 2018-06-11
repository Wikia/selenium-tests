package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import com.wikia.webdriver.common.logging.Log;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForumHistoryPageObject extends WikiBasePageObject {

  @FindBy(css = "#WallThreadHistory")
  private WebElement threadHistoryTable;
  @FindBy(css = "#WallThreadHistory tr:nth-child(1) td:nth-child(3)")
  private WebElement creatorActionCell;

  public ForumHistoryPageObject(WebDriver driver) {
    super();
    PageFactory.initElements(driver, this);
  }

  public void verifyImportandPageElements() {
    wait.forElementVisible(threadHistoryTable);
    wait.forTextInElement(creatorActionCell, "created this thread");
    Log
        .log("verifyImportandPageElements", "thread history page basic content verified", true);
  }
}
