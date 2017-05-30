package com.wikia.webdriver.pageobjectsfactory.componentobject.activity;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class WallPostActivity extends Activity {

  private WebElement diffLink;
  private WebElement activityDescriptionTextBox;

  public WallPostActivity(WebDriver driver, WebElement parentElement) {
    super(driver, parentElement);

    By diffButtonSelector = By.cssSelector("a.activityfeed-diff");
    diffLink = parentElement.findElement(diffButtonSelector);
    By articleDescriptionTextBoxSelector = By.cssSelector("tbody td:nth-child(2) p:nth-child(2)");
    activityDescriptionTextBox = parentElement.findElement(articleDescriptionTextBoxSelector);
  }

  public void showChanges() {
    diffLink.click();
  }

  public String getActivityDescription() {
    return activityDescriptionTextBox.getText();
  }
}
