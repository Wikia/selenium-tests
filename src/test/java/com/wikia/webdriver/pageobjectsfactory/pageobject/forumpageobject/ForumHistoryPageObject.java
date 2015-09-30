package com.wikia.webdriver.pageobjectsfactory.pageobject.forumpageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wikia.webdriver.common.logging.LOG;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;

public class ForumHistoryPageObject extends WikiBasePageObject {

  @FindBy(css = "#WallThreadHistory")
  private WebElement threadHistoryTable;
  @FindBy(css = "#WallThreadHistory tr:nth-child(1) td:nth-child(3)")
  private WebElement creatorActionCell;

  public ForumHistoryPageObject(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void verifyImportandPageElements() {
    wait.forElementVisible(threadHistoryTable);
    wait.forTextInElement(creatorActionCell, "created this thread");
    LOG.result("verifyImportandPageElements", "thread history page basic content verified", true);
  }
}
